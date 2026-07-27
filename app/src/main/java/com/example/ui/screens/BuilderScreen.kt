package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Component
import com.example.data.model.ComponentCategory
import com.example.ui.components.CompatibilityBadge
import com.example.ui.theme.CyanNeon
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.GreenAccent
import com.example.ui.theme.RedError
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.VioletNeon
import com.example.ui.viewmodel.BuilderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuilderScreen(
    viewModel: BuilderViewModel,
    onBack: () -> Unit,
    onSelectCategoryForCatalog: (ComponentCategory) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    val selectedComponents by viewModel.selectedComponents.collectAsState()
    val compatibilityReport by viewModel.compatibilityReport.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()
    val totalWattageDraw by viewModel.totalWattageDraw.collectAsState()
    val activeBuildName by viewModel.activeBuildName.collectAsState()

    val gamingScore by viewModel.gamingScore.collectAsState()
    val editingScore by viewModel.editingScore.collectAsState()
    val officeScore by viewModel.officeScore.collectAsState()

    var showSaveDialog by remember { mutableStateOf(false) }
    var saveNameInput by remember { mutableStateOf(activeBuildName) }
    var saveNotesInput by remember { mutableStateOf("") }
    var showCompatibilityDetail by remember { mutableStateOf(false) }

    val psuComponent = selectedComponents[ComponentCategory.PSU]
    val psuCapacity = psuComponent?.wattage ?: 0

    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = { Text("Guardar Ensamble de PC", color = TextPrimary, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text("Ingresa un nombre para guardar esta configuración en tus ensambles locales:", color = TextSecondary, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = saveNameInput,
                        onValueChange = { saveNameInput = it },
                        label = { Text("Nombre del Ensamble") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyanNeon,
                            unfocusedBorderColor = DarkBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = saveNotesInput,
                        onValueChange = { saveNotesInput = it },
                        label = { Text("Notas / Comentarios (Opcional)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = CyanNeon,
                            unfocusedBorderColor = DarkBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.saveBuild(saveNameInput, saveNotesInput) {
                            Toast.makeText(context, "Ensamble guardado con éxito", Toast.LENGTH_SHORT).show()
                        }
                        showSaveDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CyanNeon, contentColor = DarkBackground)
                ) {
                    Text("Guardar", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text("Cancelar", color = TextMuted)
                }
            },
            containerColor = DarkSurface
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(activeBuildName, color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás", tint = TextPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.clearAllComponents() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reiniciar", tint = TextMuted)
                    }
                    IconButton(onClick = {
                        val summaryText = buildString {
                            appendLine("=== $activeBuildName ===")
                            appendLine("Precio Total: \$${String.format("%.2f", totalPrice)}")
                            appendLine("Consumo de Energía: ${totalWattageDraw}W")
                            appendLine("Compatibilidad: ${if (compatibilityReport.isCompatible) "100% Compatible" else "Atención Requerida"}")
                            appendLine()
                            appendLine("Componentes:")
                            selectedComponents.forEach { (cat, comp) ->
                                appendLine("- ${cat.displayName}: ${comp.name} (\$${comp.price})")
                            }
                        }
                        clipboardManager.setText(AnnotatedString(summaryText))
                        Toast.makeText(context, "Resumen copiado al portapapeles", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Compartir", tint = CyanNeon)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkSurface)
            )
        },
        bottomBar = {
            // Persistent Bottom Bar for Saving Build
            Surface(
                color = DarkSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Costo Total", color = TextMuted, fontSize = 11.sp)
                        Text("$${String.format("%.2f", totalPrice)}", color = CyanNeon, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
                    }

                    Button(
                        onClick = {
                            saveNameInput = activeBuildName
                            showSaveDialog = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = VioletNeon, contentColor = TextPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Bookmark, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Guardar PC", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        },
        containerColor = DarkBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Live Compatibility & Power Meter Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DarkBorder, RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CompatibilityBadge(report = compatibilityReport)

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { showCompatibilityDetail = !showCompatibilityDetail }
                            ) {
                                Icon(Icons.Default.Info, contentDescription = "Detalles", tint = TextMuted, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(if (showCompatibilityDetail) "Ocultar" else "Ver Análisis", color = TextMuted, fontSize = 12.sp)
                            }
                        }

                        if (showCompatibilityDetail) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(DarkSurfaceVariant, RoundedCornerShape(12.dp))
                                    .padding(10.dp)
                            ) {
                                if (compatibilityReport.messages.isEmpty()) {
                                    Text("Selecciona componentes para verificar compatibilidad.", color = TextMuted, fontSize = 12.sp)
                                } else {
                                    compatibilityReport.messages.forEach { msg ->
                                        Row(modifier = Modifier.padding(vertical = 4.dp)) {
                                            Text(if (msg.isError) "❌ " else "✅ ", fontSize = 12.sp)
                                            Column {
                                                Text(msg.title, color = if (msg.isError) RedError else GreenAccent, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                                Text(msg.detail, color = TextSecondary, fontSize = 11.sp)
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Wattage Power Consumption Bar
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.FlashOn, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Consumo de Energía", color = TextSecondary, fontSize = 12.sp)
                            }
                            Text(
                                text = "${totalWattageDraw}W ${if (psuCapacity > 0) "/ ${psuCapacity}W Fuente" else ""}",
                                color = Color(0xFFFFD54F),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        val psuProgress = if (psuCapacity > 0) (totalWattageDraw.toFloat() / psuCapacity.toFloat()).coerceIn(0f, 1f) else 0f
                        LinearProgressIndicator(
                            progress = { psuProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp),
                            color = if (psuCapacity > 0 && totalWattageDraw > psuCapacity) RedError else Color(0xFFFFB300),
                            trackColor = DarkSurfaceVariant,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Performance Ratings Summary
                        Text("Puntuación Estimada", color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ScorePill("Gaming AAA", gamingScore, CyanNeon)
                            ScorePill("Edición 4K", editingScore, VioletNeon)
                            ScorePill("Oficina", officeScore, GreenAccent)
                        }
                    }
                }
            }

            // 8 Slot Rows
            items(ComponentCategory.values()) { category ->
                val selectedComp = selectedComponents[category]
                SlotRow(
                    category = category,
                    selectedComponent = selectedComp,
                    onSelect = { onSelectCategoryForCatalog(category) },
                    onRemove = { viewModel.removeComponent(category) }
                )
            }
        }
    }
}

@Composable
private fun ScorePill(label: String, score: Int, color: Color) {
    Surface(
        color = color.copy(alpha = 0.12f),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(0.5.dp, color.copy(alpha = 0.4f))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(label, color = TextMuted, fontSize = 10.sp)
            Text("$score%", color = color, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
private fun SlotRow(
    category: ComponentCategory,
    selectedComponent: Component?,
    onSelect: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (selectedComponent != null) CyanNeon.copy(alpha = 0.5f) else DarkBorder,
                RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (selectedComponent == null) {
                // Empty Slot
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .background(DarkSurfaceVariant, CircleShape)
                            .padding(10.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = TextMuted, modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(category.displayName, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(
                            text = if (category.isRequired) "Requerido • Toca para elegir" else "Opcional • Toca para elegir",
                            color = TextMuted,
                            fontSize = 11.sp
                        )
                    }
                }

                Button(
                    onClick = onSelect,
                    colors = ButtonDefaults.buttonColors(containerColor = DarkSurfaceVariant, contentColor = CyanNeon),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Elegir", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                // Chosen Component
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(color = CyanNeon.copy(alpha = 0.2f), shape = RoundedCornerShape(4.dp)) {
                            Text(category.displayName, color = CyanNeon, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(selectedComponent.brand, color = TextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(selectedComponent.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(2.dp))

                    Text("$${String.format("%.2f", selectedComponent.price)} • ${selectedComponent.wattage}W", color = TextSecondary, fontSize = 12.sp)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onSelect) {
                        Icon(Icons.Default.Edit, contentDescription = "Cambiar", tint = CyanNeon, modifier = Modifier.size(18.dp))
                    }
                    IconButton(onClick = onRemove) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = RedError, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}
