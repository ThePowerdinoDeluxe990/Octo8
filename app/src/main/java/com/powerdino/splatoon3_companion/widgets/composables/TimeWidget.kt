package com.powerdino.splatoon3_companion.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
fun TimeWidget(
    currentTime: String
){
    Column(
        modifier = GlanceModifier.padding(
            horizontal = 6.dp,
        ),
    ) {
        Text(
            text = currentTime
                .substringAfterLast("T")
                .replace("T", " "),
            style = TextStyle(
                color = GlanceTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}