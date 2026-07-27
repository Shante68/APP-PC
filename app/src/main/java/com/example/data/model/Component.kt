package com.example.data.model

data class Component(
    val id: String,
    val name: String,
    val brand: String, // e.g. "NVIDIA", "AMD", "Intel", "ASUS", "MSI", "Gigabyte", "Corsair", "Kingston", "Samsung", "Western Digital", "NZXT", "Cooler Master"
    val category: ComponentCategory,
    val price: Double,
    val tier: String, // "Gama Entrada", "Gama Media", "Gama Alta", "Entusiasta"
    val wattage: Int, // Wattage consumption or capacity (for PSU)
    val socket: String? = null, // "AM5", "LGA1700", "AM4"
    val ramType: String? = null, // "DDR5", "DDR4"
    val formFactor: String? = null, // "ATX", "Micro-ATX", "Mini-ITX"
    val rating: Float = 4.8f,
    val description: String,
    val specs: Map<String, String>,
    val scoreGaming: Int, // 1 to 100
    val scoreEditing: Int, // 1 to 100
    val scoreOffice: Int // 1 to 100
)

data class CompatibilityReport(
    val isCompatible: Boolean,
    val messages: List<CompatibilityMessage>
)

data class CompatibilityMessage(
    val isError: Boolean, // true for blocking error, false for warning/tip
    val title: String,
    val detail: String
)
