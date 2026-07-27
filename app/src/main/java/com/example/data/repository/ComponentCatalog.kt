package com.example.data.repository

import com.example.data.model.CompatibilityMessage
import com.example.data.model.CompatibilityReport
import com.example.data.model.Component
import com.example.data.model.ComponentCategory
import com.example.data.model.UsePurpose

object ComponentCatalog {

    val allComponents: List<Component> = listOf(
        // ==================== CPUS ====================
        Component(
            id = "cpu_amd_7800x3d",
            name = "AMD Ryzen 7 7800X3D",
            brand = "AMD",
            category = ComponentCategory.CPU,
            price = 389.00,
            tier = "Gama Alta",
            wattage = 120,
            socket = "AM5",
            ramType = "DDR5",
            rating = 4.9f,
            description = "El rey indiscutible para gaming. Tecnología 3D V-Cache para máximos FPS en juegos competitivos y AAA.",
            specs = mapOf("Núcleos/Hilos" to "8C / 16T", "Frecuencia" to "5.0 GHz", "Caché" to "96MB L3", "Proceso" to "5nm"),
            scoreGaming = 99,
            scoreEditing = 85,
            scoreOffice = 92
        ),
        Component(
            id = "cpu_amd_7600",
            name = "AMD Ryzen 5 7600",
            brand = "AMD",
            category = ComponentCategory.CPU,
            price = 199.00,
            tier = "Gama Media",
            wattage = 65,
            socket = "AM5",
            ramType = "DDR5",
            rating = 4.7f,
            description = "Excelente relación precio-rendimiento para plataforma AM5 moderna con soporte DDR5 y PCIe 5.0.",
            specs = mapOf("Núcleos/Hilos" to "6C / 12T", "Frecuencia" to "5.1 GHz", "Caché" to "32MB L3", "Proceso" to "5nm"),
            scoreGaming = 84,
            scoreEditing = 72,
            scoreOffice = 90
        ),
        Component(
            id = "cpu_intel_14700k",
            name = "Intel Core i7-14700K",
            brand = "Intel",
            category = ComponentCategory.CPU,
            price = 399.00,
            tier = "Gama Alta",
            wattage = 250,
            socket = "LGA1700",
            ramType = "DDR5",
            rating = 4.8f,
            description = "Potencia bruta híbrida con 20 núcleos. Bestia para edición de video 4K, renderizado 3D y gaming avanzado.",
            specs = mapOf("Núcleos/Hilos" to "20C (8P+12E) / 28T", "Frecuencia" to "5.6 GHz", "Caché" to "33MB L3", "Proceso" to "Intel 7"),
            scoreGaming = 95,
            scoreEditing = 96,
            scoreOffice = 95
        ),
        Component(
            id = "cpu_intel_13400",
            name = "Intel Core i5-13400",
            brand = "Intel",
            category = ComponentCategory.CPU,
            price = 175.00,
            tier = "Gama Entrada",
            wattage = 65,
            socket = "LGA1700",
            ramType = "DDR4",
            rating = 4.6f,
            description = "Procesador de 10 núcleos eficiente e ideal para tareas de oficina, estudio y gaming de presupuesto ajustado.",
            specs = mapOf("Núcleos/Hilos" to "10C (6P+4E) / 16T", "Frecuencia" to "4.6 GHz", "Caché" to "20MB L3", "Proceso" to "Intel 7"),
            scoreGaming = 72,
            scoreEditing = 68,
            scoreOffice = 94
        ),
        Component(
            id = "cpu_amd_5600g",
            name = "AMD Ryzen 5 5600G con Gráficos Radeon",
            brand = "AMD",
            category = ComponentCategory.CPU,
            price = 125.00,
            tier = "Gama Entrada",
            wattage = 65,
            socket = "AM4",
            ramType = "DDR4",
            rating = 4.7f,
            description = "Incluye GPU integrada Radeon Vega 7. Perfecto para ensambles económicos sin tarjeta gráfica dedicada.",
            specs = mapOf("Núcleos/Hilos" to "6C / 12T", "GPU Integrada" to "Radeon Vega 7", "Frecuencia" to "4.4 GHz"),
            scoreGaming = 48,
            scoreEditing = 55,
            scoreOffice = 92
        ),

        // ==================== GPUS ====================
        Component(
            id = "gpu_nvidia_4080s",
            name = "NVIDIA GeForce RTX 4080 Super 16GB",
            brand = "NVIDIA",
            category = ComponentCategory.GPU,
            price = 969.00,
            tier = "Entusiasta",
            wattage = 320,
            rating = 4.9f,
            description = "Potencia extrema en 4K con DLSS 3.5 Frame Generation y trazado de rayos de última generación.",
            specs = mapOf("VRAM" to "16GB GDDR6X", "Núcleos CUDA" to "10240", "Tecnología" to "DLSS 3.5 & Ray Tracing", "Bus" to "256-bit"),
            scoreGaming = 98,
            scoreEditing = 95,
            scoreOffice = 80
        ),
        Component(
            id = "gpu_nvidia_4070s",
            name = "NVIDIA GeForce RTX 4070 Super 12GB",
            brand = "NVIDIA",
            category = ComponentCategory.GPU,
            price = 589.00,
            tier = "Gama Alta",
            wattage = 220,
            rating = 4.9f,
            description = "El punto dulce para jugar en 1440p Ultra con FPS altos y aceleración por IA en edición de video.",
            specs = mapOf("VRAM" to "12GB GDDR6X", "Núcleos CUDA" to "7168", "Tecnología" to "DLSS 3.5 & Ray Tracing", "Bus" to "192-bit"),
            scoreGaming = 91,
            scoreEditing = 88,
            scoreOffice = 80
        ),
        Component(
            id = "gpu_amd_7800xt",
            name = "AMD Radeon RX 7800 XT 16GB",
            brand = "AMD",
            category = ComponentCategory.GPU,
            price = 489.00,
            tier = "Gama Alta",
            wattage = 263,
            rating = 4.8f,
            description = "16GB de VRAM bruta para rasterización impecable en 1440p. La favorita por relación calidad-precio.",
            specs = mapOf("VRAM" to "16GB GDDR6", "Stream Processors" to "3840", "Arquitectura" to "RDNA 3", "Bus" to "256-bit"),
            scoreGaming = 88,
            scoreEditing = 79,
            scoreOffice = 80
        ),
        Component(
            id = "gpu_nvidia_4060",
            name = "NVIDIA GeForce RTX 4060 8GB",
            brand = "NVIDIA",
            category = ComponentCategory.GPU,
            price = 289.00,
            tier = "Gama Media",
            wattage = 115,
            rating = 4.6f,
            description = "Consumo ultra bajo (115W) con DLSS 3. Rendimiento excelente en 1080p competitivo.",
            specs = mapOf("VRAM" to "8GB GDDR6", "Núcleos CUDA" to "3072", "Consumo" to "115W", "Bus" to "128-bit"),
            scoreGaming = 76,
            scoreEditing = 72,
            scoreOffice = 80
        ),
        Component(
            id = "gpu_amd_6600",
            name = "AMD Radeon RX 6600 8GB",
            brand = "AMD",
            category = ComponentCategory.GPU,
            price = 189.00,
            tier = "Gama Entrada",
            wattage = 132,
            rating = 4.7f,
            description = "Reina del presupuesto para eSports y juegos en 1080p con excelente costo por fotograma.",
            specs = mapOf("VRAM" to "8GB GDDR6", "Stream Processors" to "1792", "Arquitectura" to "RDNA 2"),
            scoreGaming = 65,
            scoreEditing = 58,
            scoreOffice = 80
        ),

        // ==================== MOTHERBOARDS ====================
        Component(
            id = "mobo_asus_b650_e",
            name = "ASUS ROG Strix B650E-F Gaming WiFi",
            brand = "ASUS",
            category = ComponentCategory.MOTHERBOARD,
            price = 259.00,
            tier = "Gama Alta",
            wattage = 35,
            socket = "AM5",
            ramType = "DDR5",
            formFactor = "ATX",
            rating = 4.8f,
            description = "Placa base AM5 con PCIe 5.0, WiFi 6E, VRM robusto de 12+2 fases y disipadores térmicos de aluminio.",
            specs = mapOf("Socket" to "AM5", "Tipo RAM" to "DDR5", "Fase VRM" to "12+2", "Red" to "WiFi 6E + 2.5G LAN"),
            scoreGaming = 90,
            scoreEditing = 90,
            scoreOffice = 85
        ),
        Component(
            id = "mobo_msi_b650m_p",
            name = "MSI PRO B650M-P Micro-ATX",
            brand = "MSI",
            category = ComponentCategory.MOTHERBOARD,
            price = 119.00,
            tier = "Gama Media",
            wattage = 25,
            socket = "AM5",
            ramType = "DDR5",
            formFactor = "Micro-ATX",
            rating = 4.6f,
            description = "Opción económica y confiable para socket AM5 con ranuras M.2 NVMe doble y soporte DDR5 a 6000MHz.",
            specs = mapOf("Socket" to "AM5", "Tipo RAM" to "DDR5", "Formato" to "Micro-ATX", "Ranuras M.2" to "2x PCIe 4.0"),
            scoreGaming = 80,
            scoreEditing = 80,
            scoreOffice = 88
        ),
        Component(
            id = "mobo_gigabyte_z790_ud",
            name = "Gigabyte Z790 UD AC Intel",
            brand = "Gigabyte",
            category = ComponentCategory.MOTHERBOARD,
            price = 189.00,
            tier = "Gama Alta",
            wattage = 40,
            socket = "LGA1700",
            ramType = "DDR5",
            formFactor = "ATX",
            rating = 4.7f,
            description = "Chipset Z790 para procesadores Intel de 13ª y 14ª Gen. Soporta overclocking y almacenamiento NVMe quádruple.",
            specs = mapOf("Socket" to "LGA1700", "Tipo RAM" to "DDR5", "Chipset" to "Z790", "Fases" to "16+1+1"),
            scoreGaming = 88,
            scoreEditing = 92,
            scoreOffice = 85
        ),
        Component(
            id = "mobo_msi_h610m",
            name = "MSI PRO H610M-G DDR4",
            brand = "MSI",
            category = ComponentCategory.MOTHERBOARD,
            price = 79.00,
            tier = "Gama Entrada",
            wattage = 20,
            socket = "LGA1700",
            ramType = "DDR4",
            formFactor = "Micro-ATX",
            rating = 4.5f,
            description = "Placa base compacta de entrada para Intel 12/13/14ª Gen. Perfecta para equipos de oficina o estudio.",
            specs = mapOf("Socket" to "LGA1700", "Tipo RAM" to "DDR4", "Formato" to "Micro-ATX"),
            scoreGaming = 60,
            scoreEditing = 60,
            scoreOffice = 90
        ),
        Component(
            id = "mobo_asus_b450m",
            name = "ASUS Prime B450M-A II AM4",
            brand = "ASUS",
            category = ComponentCategory.MOTHERBOARD,
            price = 69.00,
            tier = "Gama Entrada",
            wattage = 20,
            socket = "AM4",
            ramType = "DDR4",
            formFactor = "Micro-ATX",
            rating = 4.6f,
            description = "Placa base ultrasólida para procesadores Ryzen 3000/5000 con BIOS FlashBack y ranura M.2.",
            specs = mapOf("Socket" to "AM4", "Tipo RAM" to "DDR4", "Formato" to "Micro-ATX"),
            scoreGaming = 60,
            scoreEditing = 60,
            scoreOffice = 90
        ),

        // ==================== RAM ====================
        Component(
            id = "ram_corsair_32gb_d5",
            name = "Corsair Vengeance RGB 32GB (2x16GB) DDR5 6000MHz CL30",
            brand = "Corsair",
            category = ComponentCategory.RAM,
            price = 119.00,
            tier = "Gama Alta",
            wattage = 10,
            ramType = "DDR5",
            rating = 4.9f,
            description = "Frecuencia dulce de 6000MHz con latencia ultra baja CL30 y perfiles AMD EXPO / Intel XMP 3.0.",
            specs = mapOf("Capacidad" to "32 GB (2x16GB)", "Velocidad" to "6000 MHz", "Latencia" to "CL30", "Tipo" to "DDR5"),
            scoreGaming = 95,
            scoreEditing = 92,
            scoreOffice = 90
        ),
        Component(
            id = "ram_kingston_16gb_d5",
            name = "Kingston Fury Beast 16GB (2x8GB) DDR5 5600MHz",
            brand = "Kingston",
            category = ComponentCategory.RAM,
            price = 65.00,
            tier = "Gama Media",
            wattage = 8,
            ramType = "DDR5",
            rating = 4.7f,
            description = "Memoria DDR5 veloz y de bajo perfil, ideal para disipadores grandes y multitarea moderna.",
            specs = mapOf("Capacidad" to "16 GB (2x8GB)", "Velocidad" to "5600 MHz", "Tipo" to "DDR5"),
            scoreGaming = 82,
            scoreEditing = 75,
            scoreOffice = 92
        ),
        Component(
            id = "ram_corsair_64gb_d5",
            name = "Corsair Vengeance 64GB (2x32GB) DDR5 6000MHz",
            brand = "Corsair",
            category = ComponentCategory.RAM,
            price = 209.00,
            tier = "Entusiasta",
            wattage = 14,
            ramType = "DDR5",
            rating = 4.9f,
            description = "Capacidad colosal de 64GB para edición de video 4K/8K, escenas 3D pesadas y máquinas virtuales.",
            specs = mapOf("Capacidad" to "64 GB (2x32GB)", "Velocidad" to "6000 MHz", "Tipo" to "DDR5"),
            scoreGaming = 92,
            scoreEditing = 99,
            scoreOffice = 95
        ),
        Component(
            id = "ram_kingston_16gb_d4",
            name = "Kingston Fury Beast 16GB (2x8GB) DDR4 3200MHz",
            brand = "Kingston",
            category = ComponentCategory.RAM,
            price = 39.00,
            tier = "Gama Entrada",
            wattage = 6,
            ramType = "DDR4",
            rating = 4.8f,
            description = "Estándar confiable en DDR4 para configuraciones económicas y trabajo continuo de oficina.",
            specs = mapOf("Capacidad" to "16 GB (2x8GB)", "Velocidad" to "3200 MHz", "Tipo" to "DDR4"),
            scoreGaming = 70,
            scoreEditing = 65,
            scoreOffice = 92
        ),

        // ==================== STORAGE ====================
        Component(
            id = "ssd_samsung_990pro_2tb",
            name = "Samsung 990 PRO 2TB PCIe 4.0 NVMe M.2 SSD",
            brand = "Samsung",
            category = ComponentCategory.STORAGE,
            price = 169.00,
            tier = "Entusiasta",
            wattage = 8,
            rating = 4.9f,
            description = "Velocidad de lectura de hasta 7450 MB/s. La SSD más rápida para cargas instantáneas de juegos y edición.",
            specs = mapOf("Capacidad" to "2 TB", "Lectura" to "7450 MB/s", "Escritura" to "6900 MB/s", "Interfaz" to "PCIe 4.0 NVMe"),
            scoreGaming = 98,
            scoreEditing = 98,
            scoreOffice = 95
        ),
        Component(
            id = "ssd_wd_black_1tb",
            name = "Western Digital WD_BLACK SN770 1TB NVMe M.2",
            brand = "Western Digital",
            category = ComponentCategory.STORAGE,
            price = 74.00,
            tier = "Gama Media",
            wattage = 5,
            rating = 4.8f,
            description = "Rendimiento PCIe 4.0 a 5150 MB/s con excelente relación precio/almacenamiento para catálogo de juegos.",
            specs = mapOf("Capacidad" to "1 TB", "Lectura" to "5150 MB/s", "Interfaz" to "PCIe 4.0 NVMe"),
            scoreGaming = 88,
            scoreEditing = 82,
            scoreOffice = 95
        ),
        Component(
            id = "ssd_kingston_nv2_500gb",
            name = "Kingston NV2 500GB PCIe 4.0 NVMe",
            brand = "Kingston",
            category = ComponentCategory.STORAGE,
            price = 38.00,
            tier = "Gama Entrada",
            wattage = 4,
            rating = 4.6f,
            description = "Almacenamiento rápido y económico para sistema operativo Windows y programas de trabajo diario.",
            specs = mapOf("Capacidad" to "500 GB", "Lectura" to "3500 MB/s", "Interfaz" to "PCIe 4.0 NVMe"),
            scoreGaming = 72,
            scoreEditing = 68,
            scoreOffice = 92
        ),

        // ==================== PSU ====================
        Component(
            id = "psu_corsair_rm850x",
            name = "Corsair RM850x 850W 80 Plus Gold Modular ATX 3.0",
            brand = "Corsair",
            category = ComponentCategory.PSU,
            price = 129.00,
            tier = "Gama Alta",
            wattage = 850, // Capacity
            formFactor = "ATX",
            rating = 4.9f,
            description = "Certificación 80 Plus Gold, condensadores 100% japoneses y conector nativo PCIe 5.0 12VHPWR para RTX 40.",
            specs = mapOf("Potencia" to "850W", "Certificación" to "80 Plus Gold", "Modularidad" to "Totalmente Modular", "Garantía" to "10 Años"),
            scoreGaming = 95,
            scoreEditing = 95,
            scoreOffice = 90
        ),
        Component(
            id = "psu_msi_mag_650w",
            name = "MSI MAG A650BN 650W 80 Plus Bronze",
            brand = "MSI",
            category = ComponentCategory.PSU,
            price = 59.00,
            tier = "Gama Media",
            wattage = 650,
            formFactor = "ATX",
            rating = 4.7f,
            description = "Fuente de alimentación segura con protección OVP/OCP, ideal para ensambles de gama media.",
            specs = mapOf("Potencia" to "650W", "Certificación" to "80 Plus Bronze", "Formato" to "ATX"),
            scoreGaming = 82,
            scoreEditing = 82,
            scoreOffice = 92
        ),
        Component(
            id = "psu_evga_500w",
            name = "EVGA 500 W1 500W 80 Plus White",
            brand = "EVGA",
            category = ComponentCategory.PSU,
            price = 42.00,
            tier = "Gama Entrada",
            wattage = 500,
            formFactor = "ATX",
            rating = 4.5f,
            description = "Fuente económica de 500W con protecciones eléctricas para PCs de oficina y estudio.",
            specs = mapOf("Potencia" to "500W", "Certificación" to "80 Plus White", "Formato" to "ATX"),
            scoreGaming = 60,
            scoreEditing = 60,
            scoreOffice = 90
        ),

        // ==================== CASES ====================
        Component(
            id = "case_nzxt_h6_flow",
            name = "NZXT H6 Flow RGB Cristal Templado ATX",
            brand = "NZXT",
            category = ComponentCategory.CASE,
            price = 129.00,
            tier = "Gama Alta",
            wattage = 0,
            formFactor = "ATX",
            rating = 4.9f,
            description = "Diseño panorámico de doble cámara con 3 ventiladores RGB preinstalados y flujo de aire directo a GPU.",
            specs = mapOf("Formato Soporte" to "ATX, Micro-ATX, Mini-ITX", "Paneles" to "Cristal Templado Doble", "Ventiladores" to "3x 120mm RGB incluidos"),
            scoreGaming = 95,
            scoreEditing = 90,
            scoreOffice = 85
        ),
        Component(
            id = "case_corsair_4000d",
            name = "Corsair 4000D Airflow Mid-Tower ATX",
            brand = "Corsair",
            category = ComponentCategory.CASE,
            price = 89.00,
            tier = "Gama Media",
            wattage = 0,
            formFactor = "ATX",
            rating = 4.9f,
            description = "Panel frontal de malla de alto flujo, canalización de cables RapidRoute y estructura robusta de acero.",
            specs = mapOf("Formato Soporte" to "ATX, Micro-ATX", "Panel Frontal" to "Malla Mesh Airflow", "Garantía" to "2 Años"),
            scoreGaming = 90,
            scoreEditing = 90,
            scoreOffice = 88
        ),
        Component(
            id = "case_cooler_master_q300l",
            name = "Cooler Master MasterBox Q300L Micro-ATX",
            brand = "Cooler Master",
            category = ComponentCategory.CASE,
            price = 45.00,
            tier = "Gama Entrada",
            wattage = 0,
            formFactor = "Micro-ATX",
            rating = 4.6f,
            description = "Gabinete compacto y modular con filtros magnéticos antipolvo e I/O movible en 6 posiciones.",
            specs = mapOf("Formato Soporte" to "Micro-ATX, Mini-ITX", "Panel Lateral" to "Acrílico", "Filtros" to "Magnéticos"),
            scoreGaming = 72,
            scoreEditing = 72,
            scoreOffice = 94
        ),

        // ==================== COOLERS ====================
        Component(
            id = "cooler_nzxt_kraken_240",
            name = "NZXT Kraken 240 RGB Refrigeración Líquida AIO",
            brand = "NZXT",
            category = ComponentCategory.COOLER,
            price = 139.00,
            tier = "Gama Alta",
            wattage = 15,
            socket = "AM5/LGA1700",
            rating = 4.8f,
            description = "Pantalla LCD personalizable de 1.54\" en la bomba y radiador de 240mm para procesadores de alto rendimiento.",
            specs = mapOf("Radiador" to "240mm", "Pantalla" to "LCD Personalizable", "Sockets" to "AM5, AM4, LGA1700"),
            scoreGaming = 92,
            scoreEditing = 95,
            scoreOffice = 80
        ),
        Component(
            id = "cooler_peerless_assassin",
            name = "Thermalright Peerless Assassin 120 SE Aire Dual",
            brand = "Cooler Master",
            category = ComponentCategory.COOLER,
            price = 35.00,
            tier = "Gama Media",
            wattage = 5,
            socket = "AM5/LGA1700",
            rating = 4.9f,
            description = "Disipador de doble torre con 6 heatpipes de cobre. Rinde a la par de líquidas al triple de precio.",
            specs = mapOf("Torre" to "Doble Torre con 2x 120mm Fans", "Heatpipes" to "6x 6mm Cobre", "Sockets" to "AM5, AM4, LGA1700"),
            scoreGaming = 90,
            scoreEditing = 88,
            scoreOffice = 90
        )
    )

    fun getComponentsByCategory(category: ComponentCategory): List<Component> {
        return allComponents.filter { it.category == category }
    }

    /**
     * Auto Generate Build based on User Needs (Purpose, Budget, Brand Preference)
     */
    fun recommendBuild(
        purpose: UsePurpose,
        targetBudget: Double,
        brandPreference: String? = null // "Intel", "AMD", "NVIDIA", or null
    ): Map<ComponentCategory, Component> {
        val result = mutableMapOf<ComponentCategory, Component>()

        // 1. Select CPU based on purpose & budget & brand
        val cpus = getComponentsByCategory(ComponentCategory.CPU).filter { cpu ->
            if (brandPreference != null && (brandPreference == "Intel" || brandPreference == "AMD")) {
                cpu.brand.equals(brandPreference, ignoreCase = true)
            } else true
        }.sortedByDescending {
            when (purpose) {
                UsePurpose.JUEGOS_ALTA -> it.scoreGaming
                UsePurpose.EDICION_VIDEO -> it.scoreEditing
                UsePurpose.TRABAJO_OFICINA -> it.scoreOffice
                UsePurpose.DESARROLLO_SW -> (it.scoreEditing + it.scoreOffice) / 2
            }
        }

        val chosenCpu = cpus.firstOrNull { it.price <= targetBudget * 0.30 } ?: cpus.last()
        result[ComponentCategory.CPU] = chosenCpu

        // 2. Select Compatible Motherboard
        val mobos = getComponentsByCategory(ComponentCategory.MOTHERBOARD).filter { mobo ->
            mobo.socket == chosenCpu.socket
        }.sortedBy { mobo ->
            // Keep mobo within reasonable budget ratio
            mobo.price
        }
        val chosenMobo = mobos.firstOrNull { it.price <= targetBudget * 0.18 } ?: mobos.firstOrNull() ?: mobos.last()
        result[ComponentCategory.MOTHERBOARD] = chosenMobo

        // 3. Select Compatible RAM (DDR4 vs DDR5)
        val ramTypesNeeded = chosenMobo.ramType ?: chosenCpu.ramType ?: "DDR5"
        val rams = getComponentsByCategory(ComponentCategory.RAM).filter { ram ->
            ram.ramType == ramTypesNeeded
        }.sortedByDescending {
            when (purpose) {
                UsePurpose.EDICION_VIDEO, UsePurpose.DESARROLLO_SW -> it.scoreEditing
                else -> it.scoreGaming
            }
        }
        val chosenRam = rams.firstOrNull { it.price <= targetBudget * 0.15 } ?: rams.last()
        result[ComponentCategory.RAM] = chosenRam

        // 4. Select GPU
        val gpus = getComponentsByCategory(ComponentCategory.GPU).filter { gpu ->
            if (brandPreference != null && (brandPreference == "NVIDIA" || brandPreference == "AMD")) {
                gpu.brand.equals(brandPreference, ignoreCase = true)
            } else true
        }.sortedByDescending {
            when (purpose) {
                UsePurpose.JUEGOS_ALTA -> it.scoreGaming
                UsePurpose.EDICION_VIDEO -> it.scoreEditing
                else -> it.scoreGaming
            }
        }

        // For office, integrated graphics might be ok, but if budget allows, offer entry gpu or lowest
        val chosenGpu = if (purpose == UsePurpose.TRABAJO_OFICINA && targetBudget < 600.0 && chosenCpu.specs.containsKey("GPU Integrada")) {
            // Keep CPU with APU
            gpus.firstOrNull { it.price <= targetBudget * 0.25 }
        } else {
            gpus.firstOrNull { it.price <= targetBudget * 0.45 } ?: gpus.last()
        }
        if (chosenGpu != null) {
            result[ComponentCategory.GPU] = chosenGpu
        }

        // 5. Select Storage
        val storages = getComponentsByCategory(ComponentCategory.STORAGE).sortedByDescending { it.scoreGaming }
        val chosenStorage = storages.firstOrNull { it.price <= targetBudget * 0.12 } ?: storages.last()
        result[ComponentCategory.STORAGE] = chosenStorage

        // 6. Calculate total wattage needed so far
        val totalPowerDraw = result.values.sumOf { it.wattage }
        val recommendedPsuWattage = (totalPowerDraw * 1.3).toInt().coerceAtLeast(500)

        // 7. Select PSU
        val psus = getComponentsByCategory(ComponentCategory.PSU).filter { psu ->
            psu.wattage >= recommendedPsuWattage
        }.sortedBy { it.price }
        val chosenPsu = psus.firstOrNull() ?: getComponentsByCategory(ComponentCategory.PSU).maxByOrNull { it.wattage }!!
        result[ComponentCategory.PSU] = chosenPsu

        // 8. Select Case
        val moboFormFactor = chosenMobo.formFactor ?: "ATX"
        val cases = getComponentsByCategory(ComponentCategory.CASE).filter { pcCase ->
            pcCase.formFactor == "ATX" || pcCase.formFactor == moboFormFactor
        }.sortedBy { it.price }
        val chosenCase = cases.firstOrNull() ?: getComponentsByCategory(ComponentCategory.CASE).first()
        result[ComponentCategory.CASE] = chosenCase

        // 9. Cooler
        val coolers = getComponentsByCategory(ComponentCategory.COOLER).filter { cooler ->
            cooler.socket?.contains(chosenCpu.socket ?: "") == true || cooler.socket == null
        }.sortedBy { it.price }
        if (coolers.isNotEmpty() && (chosenCpu.tier == "Gama Alta" || chosenCpu.tier == "Entusiasta" || purpose == UsePurpose.JUEGOS_ALTA)) {
            result[ComponentCategory.COOLER] = coolers.last()
        } else if (coolers.isNotEmpty()) {
            result[ComponentCategory.COOLER] = coolers.first()
        }

        return result
    }

    /**
     * Evaluate compatibility between selected components
     */
    fun evaluateCompatibility(selectedComponents: Map<ComponentCategory, Component>): CompatibilityReport {
        val messages = mutableListOf<CompatibilityMessage>()
        var isCompatible = true

        val cpu = selectedComponents[ComponentCategory.CPU]
        val mobo = selectedComponents[ComponentCategory.MOTHERBOARD]
        val ram = selectedComponents[ComponentCategory.RAM]
        val psu = selectedComponents[ComponentCategory.PSU]
        val pcCase = selectedComponents[ComponentCategory.CASE]

        // 1. Socket Compatibility
        if (cpu != null && mobo != null) {
            if (cpu.socket != mobo.socket) {
                isCompatible = false
                messages.add(
                    CompatibilityMessage(
                        isError = true,
                        title = "Incompatibilidad de Socket",
                        detail = "El procesador (${cpu.name}) usa socket ${cpu.socket}, pero la tarjeta madre (${mobo.name}) es socket ${mobo.socket}."
                    )
                )
            } else {
                messages.add(
                    CompatibilityMessage(
                        isError = false,
                        title = "Socket Compatible (${cpu.socket})",
                        detail = "El procesador y la tarjeta madre encajan perfectamente en el socket ${cpu.socket}."
                    )
                )
            }
        }

        // 2. RAM Type Compatibility
        if (ram != null && mobo != null) {
            if (ram.ramType != mobo.ramType) {
                isCompatible = false
                messages.add(
                    CompatibilityMessage(
                        isError = true,
                        title = "Incompatibilidad de Memoria RAM",
                        detail = "La memoria RAM seleccionada es ${ram.ramType}, pero la tarjeta madre solo admite ${mobo.ramType}."
                    )
                )
            } else {
                messages.add(
                    CompatibilityMessage(
                        isError = false,
                        title = "Tipo de RAM Compatible (${ram.ramType})",
                        detail = "Las memorias RAM ${ram.ramType} son totalmente compatibles con la tarjeta madre."
                    )
                )
            }
        }

        // 3. Power Supply Wattage Check
        val totalWattageDraw = selectedComponents.values.sumOf { if (it.category != ComponentCategory.PSU) it.wattage else 0 }
        if (psu != null) {
            val psuCapacity = psu.wattage
            if (psuCapacity < totalWattageDraw) {
                isCompatible = false
                messages.add(
                    CompatibilityMessage(
                        isError = true,
                        title = "Fuente de Poder Insuficiente",
                        detail = "El consumo estimado del sistema es de ${totalWattageDraw}W, pero tu fuente solo entrega ${psuCapacity}W."
                    )
                )
            } else if (psuCapacity < (totalWattageDraw * 1.15).toInt()) {
                messages.add(
                    CompatibilityMessage(
                        isError = false,
                        title = "Advertencia de Margen de Energía",
                        detail = "Tu fuente (${psuCapacity}W) soporta el sistema (${totalWattageDraw}W), pero se recomienda un margen del 20% para picos de energía."
                    )
                )
            } else {
                messages.add(
                    CompatibilityMessage(
                        isError = false,
                        title = "Alimentación de Energía Óptima",
                        detail = "Fuente de ${psuCapacity}W ideal para una carga estimada de ${totalWattageDraw}W."
                    )
                )
            }
        }

        // 4. Form Factor Check
        if (mobo != null && pcCase != null) {
            if (pcCase.formFactor == "Micro-ATX" && mobo.formFactor == "ATX") {
                isCompatible = false
                messages.add(
                    CompatibilityMessage(
                        isError = true,
                        title = "Factor de Forma Incompatible",
                        detail = "La tarjeta madre (${mobo.formFactor}) es demasiado grande para este gabinete (${pcCase.formFactor})."
                    )
                )
            }
        }

        return CompatibilityReport(
            isCompatible = isCompatible,
            messages = messages
        )
    }
}
