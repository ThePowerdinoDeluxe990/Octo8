package com.powerdino.splatoon3_companion.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.LocalSize

enum class WidgetSize {
    EXTRASMALL, SMALL, MEDIUM, LARGE
}

@Composable
fun getWidgetSize(): WidgetSize {
    val size = LocalSize.current

    return when {
        size.width < 140.dp -> WidgetSize.EXTRASMALL
        size.width < 200.dp -> WidgetSize.SMALL
        size.width < 260.dp -> WidgetSize.MEDIUM
        else -> WidgetSize.LARGE
    }
}