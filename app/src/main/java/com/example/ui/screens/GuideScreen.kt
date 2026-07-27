package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

data class GuideItem(
    val question: String,
    val category: String,
    val answer: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(
    onBack: () -> Unit
) {
    val guides = listOf(
        GuideItem(
            question = "¿Cómo saber cuántos vatios (Watts) necesita mi fuente de poder?",
            category = "Fuente de Poder (PSU)",
            answer = "Suma el consumo de cada componente (principalmente GPU + CPU). Añade un margen de seguridad del 20% al 30% para absorber picos de energía y permitir futuras actualizaciones."
        ),
        GuideItem(
            question = "¿Cuál es la diferencia entre socket AM5 e Intel LGA1700?",
            category = "Sockets & Procesadores",
            answer = "AM5 pertenece a la generación reciente de AMD (Ryzen 7000/8000/9000) y requiere obligatoriamente memorias DDR5. LGA1700 pertenece a Intel (12ª, 13ª y 14ª Gen) y existen placas compatibles con DDR4 o DDR5."
        ),
        GuideItem(
            question = "¿Vale la pena pagar por memoria RAM DDR5 sobre DDR4?",
            category = "Memoria RAM",
            answer = "Para juegos de última generación y edición de video 4K/8K, DDR5 ofrece entre un 10% y un 25% más de rendimiento y ancho de banda. Si buscas presupuesto extremo, DDR4 sigue siendo funcional."
        ),
        GuideItem(
            question = "¿Qué tarjeta gráfica necesito para jugar en 1080p, 1440p o 4K?",
            category = "Tarjetas Gráficas (GPU)",
            answer = "1080p: RTX 4060 o RX 6600.\n1440p: RTX 4070 Super o RX 7800 XT.\n4K Ultra: RTX 4080 Super o RX 7900 XTX."
        ),
        GuideItem(
            question = "¿Qué es el cuello de botella (Bottleneck)?",
            category = "Rendimiento",
            answer = "Ocurre cuando un componente (por ejemplo, un procesador antiguo de gama baja) limita la capacidad de rendimiento de una tarjeta gráfica de alta gama, impidiéndole entregar sus FPS máximos."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Guía de Compatibilidad", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        },
        containerColor = DarkBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(guides) { guide ->
                GuideCard(guide = guide)
            }
        }
    }
}

@Composable
private fun GuideCard(guide: GuideItem) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .border(1.dp, DarkBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.HelpOutline, contentDescription = null, tint = CyanNeon, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(guide.category.uppercase(), color = CyanNeon, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(guide.question, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = TextMuted
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(guide.answer, color = TextSecondary, fontSize = 13.sp, lineHeight = 18.sp)
                }
            }
        }
    }
}
