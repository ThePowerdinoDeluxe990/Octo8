package com.powerdino.splatoon3_companion.widgets.composables

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.powerdino.splatoon3_companion.R


@SuppressLint("RestrictedApi")
@Composable
fun MapCardWidget(
    mapName:String,
    mapImage:Int,
    modifier: GlanceModifier,
){
    Box(
        modifier
    ){
        Image(
            provider = ImageProvider(mapImage),
            contentDescription = mapName,
            contentScale = ContentScale.FillBounds,
            modifier = GlanceModifier
        )

        Box(
            modifier = GlanceModifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text=mapName,
                modifier = GlanceModifier
                    .padding(2.dp),
                maxLines = 2,
                style = TextStyle(
                    color = ColorProvider(Color.White),
                    fontWeight = FontWeight.Bold,

                )
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Composable
@androidx.glance.preview.Preview
private fun Preview(){
   MapCardWidget(
       mapName = "Stage 1",
       mapImage = R.drawable.stage1,
       modifier = GlanceModifier
   )
}