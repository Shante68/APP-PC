package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Component
import com.example.data.model.ComponentCategory
import com.example.ui.components.ComponentCard
import com.example.ui.components.ComponentDetailDialog
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.BuilderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: BuilderViewModel,
    onBack: () -> Unit
) {
    val components by viewModel.filteredComponents.collectAsState()
    val selectedComponents by viewModel.selectedComponents.collectAsState()

    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategoryFilter.collectAsState()
    val selectedBrand by viewModel.selectedBrandFilter.collectAsState()
    val selectedTier by viewModel.selectedTierFilter.collectAsState()
    val maxBudgetFilter by viewModel.maxBudgetFilter.collectAsState()

    var activeDialogComponent by remember { mutableStateOf<Component?>(null) }
    var showFilterPanel by remember { mutableStateOf(false) }

    val brandsList = listOf("Intel", "AMD", "NVIDIA", "ASUS", "MSI", "Gigabyte", "Corsair", "Kingston", "Samsung", "Western Digital", "NZXT", "Cooler Master")
    val tiersList = listOf("Gama Entrada", "Gama Media", "Gama Alta", "Entusiasta")

    if (activeDialogComponent != null) {
        val comp = activeDialogComponent!!
        val isAlreadySelected = selectedComponents[comp.category]?.id == comp.id
        ComponentDetailDialog(
            component = comp,
            isSelected = isAlreadySelected,
            onDismiss = { activeDialogComponent = null },
            onSelect = {
                if (isAlreadySelected) {
                    viewModel.removeComponent(comp.category)
                } else {
                    viewModel.selectComponent(comp)
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Componentes", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = TextPrimary)
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showFilterPanel = !showFilterPanel },
                        modifier = Modifier
                            .background(if (showFilterPanel) CyanNeon.copy(alpha = 0.2f) else Color.Transparent, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filtros",
                            tint = if (showFilterPanel) CyanNeon else TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        },
        containerColor = DarkBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Search Input Bar
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = { Text("Buscar procesador, GPU, RAM, marca...", color = TextMuted) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextMuted) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Borrar", tint = TextMuted)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkSurface,
                        unfocusedContainerColor = DarkSurface,
                        focusedBorderColor = CyanNeon,
                        unfocusedBorderColor = DarkBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    singleLine = true
                )
            }

            // Category Scrollable Tabs
            LazyRow(
                modifier = Modifier.padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    CategoryChip(
                        title = "Todas las Categorías",
                        isSelected = selectedCategory == null,
                        onClick = { viewModel.setCategoryFilter(null) }
                    )
                }
                items(ComponentCategory.values()) { cat ->
                    CategoryChip(
                        title = cat.displayName,
                        isSelected = selectedCategory == cat,
                        onClick = { viewModel.setCategoryFilter(if (selectedCategory == cat) null else cat) }
                    )
                }
            }

            // Expandable Filter Panel (Brands & Budget)
            if (showFilterPanel) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .border(1.dp, DarkBorder, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Filtros Avanzados", color = CyanNeon, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(
                                text = "Limpiar Todo",
                                color = TextMuted,
                                fontSize = 11.sp,
                                modifier = Modifier.clickable {
                                    viewModel.setBrandFilter(null)
                                    viewModel.setTierFilter(null)
                                    viewModel.setMaxBudgetFilter(1500.0)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Budget Max Slider Filter
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Presupuesto Máximo por Componente", color = TextSecondary, fontSize = 12.sp)
                            Text("$${maxBudgetFilter.toInt()} USD", color = CyanNeon, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                        Slider(
                            value = maxBudgetFilter.toFloat(),
                            onValueChange = { viewModel.setMaxBudgetFilter(it.toDouble()) },
                            valueRange = 30f..1500f,
                            colors = SliderDefaults.colors(thumbColor = CyanNeon, activeTrackColor = CyanNeon)
                        )

                        // Brand Filter Chips
                        Text("Filtrar por Marca", color = TextSecondary, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(brandsList) { brand ->
                                FilterChip(
                                    label = brand,
                                    isSelected = selectedBrand == brand,
                                    onClick = { viewModel.setBrandFilter(if (selectedBrand == brand) null else brand) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Tier Filter Chips
                        Text("Gama / Nivel", color = TextSecondary, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(tiersList) { tier ->
                                FilterChip(
                                    label = tier,
                                    isSelected = selectedTier == tier,
                                    onClick = { viewModel.setTierFilter(if (selectedTier == tier) null else tier) }
                                )
                            }
                        }
                    }
                }
            }

            // Results count label
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mostrando ${components.size} componentes",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }

            // Component List
            if (components.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Sin resultados", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Intenta ajustar el presupuesto o borrar los filtros de marca.", color = TextMuted, fontSize = 13.sp)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(components) { component ->
                        val isSelected = selectedComponents[component.category]?.id == component.id
                        ComponentCard(
                            component = component,
                            isSelected = isSelected,
                            onSelect = {
                                if (isSelected) {
                                    viewModel.removeComponent(component.category)
                                } else {
                                    viewModel.selectComponent(component)
                                }
                            },
                            onDetailClick = {
                                activeDialogComponent = component
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryChip(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable { onClick() }
            .border(
                width = if (isSelected) 1.dp else 0.5.dp,
                color = if (isSelected) CyanNeon else DarkBorder,
                shape = RoundedCornerShape(20.dp)
            ),
        color = if (isSelected) Color(0xFF003840) else DarkSurface,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = title,
            color = if (isSelected) CyanNeon else TextSecondary,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable { onClick() }
            .border(
                width = if (isSelected) 1.dp else 0.5.dp,
                color = if (isSelected) CyanNeon else DarkBorder,
                shape = RoundedCornerShape(8.dp)
            ),
        color = if (isSelected) Color(0xFF13222B) else DarkSurfaceVariant,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = label,
            color = if (isSelected) CyanNeon else TextSecondary,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
