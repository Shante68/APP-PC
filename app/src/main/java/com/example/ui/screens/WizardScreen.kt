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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.UsePurpose
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.GreenAccent
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardScreen(
    initialPurpose: UsePurpose?,
    onBack: () -> Unit,
    onGenerateAndOpenBuilder: (UsePurpose, Double, String?) -> Unit
) {
    var selectedPurpose by remember { mutableStateOf(initialPurpose ?: UsePurpose.JUEGOS_ALTA) }
    var selectedBudget by remember { mutableStateOf(1500.0) }
    var selectedBrandPref by remember { mutableStateOf<String?>(null) } // null = Sin preferencia

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Asistente Inteligente de PC",
                        color = TextPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás",
                            tint = TextPrimary
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Intro Banner
            Text(
                text = "Paso 1: ¿Cuál es el uso principal de tu computadora?",
                color = CyanNeon,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Purpose Options Grid
            PurposeOptionCard(
                purpose = UsePurpose.JUEGOS_ALTA,
                icon = Icons.Default.SportsEsports,
                isSelected = selectedPurpose == UsePurpose.JUEGOS_ALTA,
                onClick = {
                    selectedPurpose = UsePurpose.JUEGOS_ALTA
                    selectedBudget = 1500.0
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            PurposeOptionCard(
                purpose = UsePurpose.EDICION_VIDEO,
                icon = Icons.Default.Edit,
                isSelected = selectedPurpose == UsePurpose.EDICION_VIDEO,
                onClick = {
                    selectedPurpose = UsePurpose.EDICION_VIDEO
                    selectedBudget = 1800.0
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            PurposeOptionCard(
                purpose = UsePurpose.TRABAJO_OFICINA,
                icon = Icons.Default.Computer,
                isSelected = selectedPurpose == UsePurpose.TRABAJO_OFICINA,
                onClick = {
                    selectedPurpose = UsePurpose.TRABAJO_OFICINA
                    selectedBudget = 500.0
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            PurposeOptionCard(
                purpose = UsePurpose.DESARROLLO_SW,
                icon = Icons.Default.Code,
                isSelected = selectedPurpose == UsePurpose.DESARROLLO_SW,
                onClick = {
                    selectedPurpose = UsePurpose.DESARROLLO_SW
                    selectedBudget = 1100.0
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Step 2: Budget Slider
            Text(
                text = "Paso 2: ¿Cuál es tu presupuesto estimado?",
                color = CyanNeon,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
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
                        Text(
                            text = "Presupuesto Objetivo",
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "$${selectedBudget.toInt()} USD",
                            color = CyanNeon,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Slider(
                        value = selectedBudget.toFloat(),
                        onValueChange = { selectedBudget = it.toDouble() },
                        valueRange = 350f..4000f,
                        steps = 73,
                        colors = SliderDefaults.colors(
                            thumbColor = CyanNeon,
                            activeTrackColor = CyanNeon,
                            inactiveTrackColor = DarkSurfaceVariant
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("$350 USD (Económico)", color = TextMuted, fontSize = 11.sp)
                        Text("$4,000 USD (Entusiasta)", color = TextMuted, fontSize = 11.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Step 3: Brand Preference
            Text(
                text = "Paso 3: Preferencia de Marca (Opcional)",
                color = CyanNeon,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BrandChip("Cualquiera", isSelected = selectedBrandPref == null, onClick = { selectedBrandPref = null }, modifier = Modifier.weight(1f))
                BrandChip("Intel", isSelected = selectedBrandPref == "Intel", onClick = { selectedBrandPref = "Intel" }, modifier = Modifier.weight(1f))
                BrandChip("AMD", isSelected = selectedBrandPref == "AMD", onClick = { selectedBrandPref = "AMD" }, modifier = Modifier.weight(1f))
                BrandChip("NVIDIA", isSelected = selectedBrandPref == "NVIDIA", onClick = { selectedBrandPref = "NVIDIA" }, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Action Button
            Button(
                onClick = {
                    onGenerateAndOpenBuilder(selectedPurpose, selectedBudget, selectedBrandPref)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VioletNeon,
                    contentColor = TextPrimary
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Generar Ensamble Automático",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun PurposeOptionCard(
    purpose: UsePurpose,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) CyanNeon else DarkBorder,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF13222B) else DarkSurface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(if (isSelected) CyanNeon else DarkSurfaceVariant, CircleShape)
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) DarkBackground else TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = purpose.title,
                    color = TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = purpose.subtitle,
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = CyanNeon
                )
            }
        }
    }
}

@Composable
private fun BrandChip(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { onClick() }
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) CyanNeon else DarkBorder,
                shape = RoundedCornerShape(12.dp)
            ),
        color = if (isSelected) Color(0xFF13222B) else DarkSurface,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = if (isSelected) CyanNeon else TextSecondary,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
