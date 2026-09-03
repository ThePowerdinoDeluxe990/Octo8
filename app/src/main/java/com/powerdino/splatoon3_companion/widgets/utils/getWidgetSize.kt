package com.powerdino.splatoon3_companion.widgets.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.LocalSize


@Composable
fun getWidgetSize(
    sizeWidth: Dp
): Dp {
    val size = LocalSize.current

    return when {
        size.width < 140.dp ->  32.dp
        size.width < 200.dp ->  48.dp
        size.width < 260.dp ->  128.dp
        else -> sizeWidth /2  - 28.dp
    }
}