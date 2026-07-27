package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.SavedBuildEntity
import com.example.data.model.CompatibilityReport
import com.example.data.model.Component
import com.example.data.model.ComponentCategory
import com.example.data.model.UsePurpose
import com.example.data.repository.BuildRepository
import com.example.data.repository.ComponentCatalog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BuilderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BuildRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = BuildRepository(database.savedBuildDao())
    }

    val savedBuilds: StateFlow<List<SavedBuildEntity>> = repository.allSavedBuilds
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Active Build State
    private val _selectedComponents = MutableStateFlow<Map<ComponentCategory, Component>>(emptyMap())
    val selectedComponents: StateFlow<Map<ComponentCategory, Component>> = _selectedComponents.asStateFlow()

    private val _currentPurpose = MutableStateFlow(UsePurpose.JUEGOS_ALTA)
    val currentPurpose: StateFlow<UsePurpose> = _currentPurpose.asStateFlow()

    private val _targetBudget = MutableStateFlow(1500.0)
    val targetBudget: StateFlow<Double> = _targetBudget.asStateFlow()

    private val _activeBuildName = MutableStateFlow("Mi PC RigCraft")
    val activeBuildName: StateFlow<String> = _activeBuildName.asStateFlow()

    // Filtering State for Catalog
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow<ComponentCategory?>(null)
    val selectedCategoryFilter: StateFlow<ComponentCategory?> = _selectedCategoryFilter.asStateFlow()

    private val _selectedBrandFilter = MutableStateFlow<String?>(null)
    val selectedBrandFilter: StateFlow<String?> = _selectedBrandFilter.asStateFlow()

    private val _selectedTierFilter = MutableStateFlow<String?>(null)
    val selectedTierFilter: StateFlow<String?> = _selectedTierFilter.asStateFlow()

    private val _maxBudgetFilter = MutableStateFlow(1500.0)
    val maxBudgetFilter: StateFlow<Double> = _maxBudgetFilter.asStateFlow()

    // Filtered Catalog Items Flow
    val filteredComponents: StateFlow<List<Component>> = combine(
        _searchQuery,
        _selectedCategoryFilter,
        _selectedBrandFilter,
        _selectedTierFilter,
        _maxBudgetFilter
    ) { query, category, brand, tier, maxPrice ->
        ComponentCatalog.allComponents.filter { comp ->
            val matchesQuery = query.isEmpty() || comp.name.contains(query, ignoreCase = true) || comp.brand.contains(query, ignoreCase = true)
            val matchesCategory = category == null || comp.category == category
            val matchesBrand = brand == null || comp.brand.equals(brand, ignoreCase = true)
            val matchesTier = tier == null || comp.tier.equals(tier, ignoreCase = true)
            val matchesPrice = comp.price <= maxPrice

            matchesQuery && matchesCategory && matchesBrand && matchesTier && matchesPrice
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ComponentCatalog.allComponents)

    // Derived Compatibility
    val compatibilityReport: StateFlow<CompatibilityReport> = _selectedComponents
        .map { comps ->
            ComponentCatalog.evaluateCompatibility(comps)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CompatibilityReport(true, emptyList()))

    // Total Cost
    val totalPrice: StateFlow<Double> = _selectedComponents
        .map { comps ->
            comps.values.sumOf { it.price }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    // Total Wattage Draw
    val totalWattageDraw: StateFlow<Int> = _selectedComponents
        .map { comps ->
            comps.values.sumOf { if (it.category != ComponentCategory.PSU) it.wattage else 0 }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Performance Ratings (Out of 100)
    val gamingScore: StateFlow<Int> = _selectedComponents
        .map { comps ->
            val cpu = comps[ComponentCategory.CPU]?.scoreGaming ?: 0
            val gpu = comps[ComponentCategory.GPU]?.scoreGaming ?: 0
            val ram = comps[ComponentCategory.RAM]?.scoreGaming ?: 0
            if (comps.isEmpty()) 0 else ((cpu * 0.3) + (gpu * 0.55) + (ram * 0.15)).toInt()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val editingScore: StateFlow<Int> = _selectedComponents
        .map { comps ->
            val cpu = comps[ComponentCategory.CPU]?.scoreEditing ?: 0
            val gpu = comps[ComponentCategory.GPU]?.scoreEditing ?: 0
            val ram = comps[ComponentCategory.RAM]?.scoreEditing ?: 0
            val storage = comps[ComponentCategory.STORAGE]?.scoreEditing ?: 0
            if (comps.isEmpty()) 0 else ((cpu * 0.4) + (gpu * 0.3) + (ram * 0.2) + (storage * 0.1)).toInt()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val officeScore: StateFlow<Int> = _selectedComponents
        .map { comps ->
            val cpu = comps[ComponentCategory.CPU]?.scoreOffice ?: 0
            val ram = comps[ComponentCategory.RAM]?.scoreOffice ?: 0
            val storage = comps[ComponentCategory.STORAGE]?.scoreOffice ?: 0
            if (comps.isEmpty()) 0 else ((cpu * 0.5) + (ram * 0.3) + (storage * 0.2)).toInt()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Action Methods
    fun selectComponent(component: Component) {
        val current = _selectedComponents.value.toMutableMap()
        current[component.category] = component
        _selectedComponents.value = current
    }

    fun removeComponent(category: ComponentCategory) {
        val current = _selectedComponents.value.toMutableMap()
        current.remove(category)
        _selectedComponents.value = current
    }

    fun clearAllComponents() {
        _selectedComponents.value = emptyMap()
    }

    fun setBuildName(name: String) {
        _activeBuildName.value = name
    }

    fun generateRecommendedBuild(purpose: UsePurpose, budget: Double, brandPref: String? = null) {
        _currentPurpose.value = purpose
        _targetBudget.value = budget
        _activeBuildName.value = "PC ${purpose.title} ($${budget.toInt()})"
        val recommended = ComponentCatalog.recommendBuild(purpose, budget, brandPref)
        _selectedComponents.value = recommended
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setCategoryFilter(category: ComponentCategory?) {
        _selectedCategoryFilter.value = category
    }

    fun setBrandFilter(brand: String?) {
        _selectedBrandFilter.value = brand
    }

    fun setTierFilter(tier: String?) {
        _selectedTierFilter.value = tier
    }

    fun setMaxBudgetFilter(max: Double) {
        _maxBudgetFilter.value = max
    }

    fun saveBuild(name: String, notes: String, onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            repository.saveBuild(
                buildName = if (name.isBlank()) _activeBuildName.value else name,
                purposeName = _currentPurpose.value.name,
                selectedComponents = _selectedComponents.value,
                notes = notes
            )
            onComplete()
        }
    }

    fun deleteSavedBuild(id: Int) {
        viewModelScope.launch {
            repository.deleteBuild(id)
        }
    }

    fun loadSavedBuild(entity: SavedBuildEntity) {
        val mapped = repository.mapEntityToComponents(entity)
        _selectedComponents.value = mapped
        _activeBuildName.value = entity.buildName
        try {
            _currentPurpose.value = UsePurpose.valueOf(entity.purposeName)
        } catch (e: Exception) {
            _currentPurpose.value = UsePurpose.JUEGOS_ALTA
        }
    }
}
