package com.example.data.model

enum class UsePurpose(
    val title: String,
    val subtitle: String,
    val description: String,
    val recommendedMinBudget: Double,
    val recommendedMaxBudget: Double
) {
    JUEGOS_ALTA(
        title = "Juegos de Alta Exigencia",
        subtitle = "Gaming 1440p / 4K & Ray Tracing",
        description = "Optimizado para exprimir FPS en títulos AAA (Cyberpunk 2077, COD, GTA VI) con soporte para Ray Tracing y altas frecuencias.",
        recommendedMinBudget = 1100.0,
        recommendedMaxBudget = 3200.0
    ),
    EDICION_VIDEO(
        title = "Edición de Video y 3D",
        subtitle = "Premiere, DaVinci, Blender, After Effects",
        description = "Prioriza procesadores multinúcleo, abundante memoria RAM (32GB+) y GPUs potentes para renderizado ultrarrápido en 4K.",
        recommendedMinBudget = 1000.0,
        recommendedMaxBudget = 3500.0
    ),
    TRABAJO_OFICINA(
        title = "Trabajo de Oficina y Estudio",
        subtitle = "Productividad, Excel, Zoom y Multitarea",
        description = "Configuración silenciosa, eficiente en consumo y económica. Ideal para documentos, navegación intensiva y videoconferencias sin pausa.",
        recommendedMinBudget = 350.0,
        recommendedMaxBudget = 750.0
    ),
    DESARROLLO_SW(
        title = "Desarrollo de Software y VMs",
        subtitle = "Programación, Docker, Android Studio y Cloud",
        description = "Enfocado en compilar código velozmente, ejecutar múltiples contenedores y máquinas virtuales con SSD NVMe ultra rápido.",
        recommendedMinBudget = 700.0,
        recommendedMaxBudget = 1800.0
    )
}
