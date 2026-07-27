package com.example.data.model

enum class ComponentCategory(val displayName: String, val iconResName: String, val isRequired: Boolean) {
    CPU("Procesador (CPU)", "ic_cpu", true),
    GPU("Tarjeta Gráfica (GPU)", "ic_gpu", true),
    MOTHERBOARD("Placa Base", "ic_motherboard", true),
    RAM("Memoria RAM", "ic_ram", true),
    STORAGE("Almacenamiento SSD", "ic_storage", true),
    PSU("Fuente de Poder", "ic_psu", true),
    CASE("Gabinete / Case", "ic_case", true),
    COOLER("Refrigeración", "ic_cooler", false)
}
