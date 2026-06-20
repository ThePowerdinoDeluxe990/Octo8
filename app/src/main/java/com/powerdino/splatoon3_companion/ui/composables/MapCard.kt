package com.powerdino.splatoon3_companion.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R

@Composable
fun MapCard(
    mapName:String,
    mapImage:Int,
){

    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ){
        Column(
        ){
            Image(
                painter = painterResource(mapImage),
                contentDescription = mapName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .size(128.dp)
            )
            Text(
                text=mapName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp),

            )

        }

    }
}

@Preview(
    showBackground = true,
    name = "Light",
    showSystemUi = true,
    backgroundColor = 0xFFFCFCFF
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark",
    showSystemUi = true,
    backgroundColor = 0xFF1A1C1E
)
@Composable
fun PreviewMapCard(){
    MapCard(
        mapName= "Map 1",
        mapImage = R.drawable.stage1
    )
}