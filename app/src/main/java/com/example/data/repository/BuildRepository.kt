package com.example.data.repository

import com.example.data.local.SavedBuildDao
import com.example.data.local.SavedBuildEntity
import com.example.data.model.Component
import com.example.data.model.ComponentCategory
import kotlinx.coroutines.flow.Flow

class BuildRepository(private val dao: SavedBuildDao) {

    val allSavedBuilds: Flow<List<SavedBuildEntity>> = dao.getAllSavedBuilds()

    suspend fun saveBuild(
        buildName: String,
        purposeName: String,
        selectedComponents: Map<ComponentCategory, Component>,
        notes: String = ""
    ): Long {
        val totalPrice = selectedComponents.values.sumOf { it.price }
        val totalWattage = selectedComponents.values.sumOf { if (it.category != ComponentCategory.PSU) it.wattage else 0 }

        val entity = SavedBuildEntity(
            buildName = buildName,
            purposeName = purposeName,
            totalPrice = totalPrice,
            totalWattage = totalWattage,
            cpuId = selectedComponents[ComponentCategory.CPU]?.id,
            gpuId = selectedComponents[ComponentCategory.GPU]?.id,
            moboId = selectedComponents[ComponentCategory.MOTHERBOARD]?.id,
            ramId = selectedComponents[ComponentCategory.RAM]?.id,
            storageId = selectedComponents[ComponentCategory.STORAGE]?.id,
            psuId = selectedComponents[ComponentCategory.PSU]?.id,
            caseId = selectedComponents[ComponentCategory.CASE]?.id,
            coolerId = selectedComponents[ComponentCategory.COOLER]?.id,
            notes = notes
        )

        return dao.insertBuild(entity)
    }

    suspend fun deleteBuild(id: Int) {
        dao.deleteBuildById(id)
    }

    fun mapEntityToComponents(entity: SavedBuildEntity): Map<ComponentCategory, Component> {
        val result = mutableMapOf<ComponentCategory, Component>()
        val ids = mapOf(
            ComponentCategory.CPU to entity.cpuId,
            ComponentCategory.GPU to entity.gpuId,
            ComponentCategory.MOTHERBOARD to entity.moboId,
            ComponentCategory.RAM to entity.ramId,
            ComponentCategory.STORAGE to entity.storageId,
            ComponentCategory.PSU to entity.psuId,
            ComponentCategory.CASE to entity.caseId,
            ComponentCategory.COOLER to entity.coolerId
        )

        ids.forEach { (category, id) ->
            if (id != null) {
                val found = ComponentCatalog.allComponents.firstOrNull { it.id == id }
                if (found != null) {
                    result[category] = found
                }
            }
        }

        return result
    }
}
