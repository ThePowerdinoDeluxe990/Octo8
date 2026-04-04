package com.powerdino.splatoon3_companion.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LinkButton(
    onClick:() -> Unit,
    icon: ImageVector,
    description:String,
    text:String
){
    FilledTonalButton(
        onClick,
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(
                width = 96.dp,
                height = 50.dp

            )

        ){
            Icon(
                imageVector = icon,
                contentDescription = description
            )
            Text(text)
        }
    }
}