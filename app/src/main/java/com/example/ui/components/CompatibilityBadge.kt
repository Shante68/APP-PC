package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CompatibilityReport

@Composable
fun CompatibilityBadge(
    report: CompatibilityReport,
    modifier: Modifier = Modifier
) {
    val (bgColor, borderColor, textColor, icon, label) = when {
        !report.isCompatible -> Tuple5(
            Color(0xFF3B1215),
            Color(0xFFFF5252),
            Color(0xFFFF8A8A),
            Icons.Default.Error,
            "INCOMPATIBLE"
        )
        report.messages.any { !it.isError } -> Tuple5(
            Color(0xFF0F2B1D),
            Color(0xFF00E676),
            Color(0xFF80FFB4),
            Icons.Default.CheckCircle,
            "100% COMPATIBLE"
        )
        else -> Tuple5(
            Color(0xFF332500),
            Color(0xFFFFB300),
            Color(0xFFFFD54F),
            Icons.Default.Warning,
            "VERIFICANDO"
        )
    }

    Box(
        modifier = modifier
            .background(bgColor, shape = RoundedCornerShape(20.dp))
            .border(1.dp, borderColor, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = borderColor,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = label,
                color = textColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private data class Tuple5<A, B, C, D, E>(
    val a: A, val b: B, val c: C, val d: D, val e: E
)
