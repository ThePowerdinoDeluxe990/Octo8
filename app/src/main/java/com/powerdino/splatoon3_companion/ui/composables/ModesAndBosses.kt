package com.powerdino.splatoon3_companion.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R

val CompetitiveModesList = listOf(
    R.drawable.zones,
    R.drawable.rainmaker,
    R.drawable.clam,
    R.drawable.tower
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModesAndBosses(
    modeOrBoss:String,
    bossName:String?,
){
    var nameOfMode: String
    var imageOfMode: Int

    when(modeOrBoss){
        "Goal" -> {
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[1]
        }
        "Lift" ->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[3]
        }
        "Clam"-> {
            nameOfMode =bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[2]
        }
        "Area"->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[0]
        }
        "Triple"->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = R.drawable.s3_icon_triumvirate
        }
        "SakelienGiant"->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = R.drawable.s3_icon_cohozuna
        }
        "SakeRope"->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = R.drawable.s3_icon_horrorboros
        }
        "SakeJaw"->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = R.drawable.s3_icon_megalodontia
        }
        "Paint"->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = R.drawable.turfwar
        }
        else ->{
            nameOfMode = "Unknown"
            imageOfMode = R.drawable.s3_icon_triumvirate
        }
    }
    Row(
        modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 2.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(imageOfMode),
            contentDescription = nameOfMode,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(32.dp)
        )

        Text(
            text= bossName.toString()
        )
    }

}


@Preview
@Composable
fun PreviewModesAndBosses(){
    ModesAndBosses(
        modeOrBoss = "Area",
        bossName = null,
    )
}