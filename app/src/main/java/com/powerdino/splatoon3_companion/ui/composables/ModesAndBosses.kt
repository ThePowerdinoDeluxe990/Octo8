package com.powerdino.splatoon3_companion.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.lists.CompetitiveModesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModesAndBosses(
    modeOrBoss:String,
    bossName:String?,
){
    var nameOfMode = ""
    var imageOfMode = 0

    when(modeOrBoss){
        "Goal" -> {
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[1].imageState
        }
        "Lift" ->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[3].imageState
        }
        "Clam"-> {
            nameOfMode =bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[2].imageState
        }
        "Area"->{
            nameOfMode = bossName ?: modeOrBoss
            imageOfMode = CompetitiveModesList[0].imageState
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
        TooltipBox(
            modifier = Modifier,
            positionProvider =  TooltipDefaults  .rememberTooltipPositionProvider(
                TooltipAnchorPosition.Above),
            tooltip = {
                PlainTooltip { Text(
                    text= nameOfMode,
                    style = MaterialTheme.typography.titleMedium,
                )}
            },
            state = rememberTooltipState()
        ) {
            Image(
                painter = painterResource(imageOfMode),
                contentDescription = nameOfMode,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(32.dp)
            )
        }

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