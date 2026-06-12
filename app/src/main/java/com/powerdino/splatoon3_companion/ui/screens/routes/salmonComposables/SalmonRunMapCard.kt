package com.powerdino.splatoon3_companion.ui.screens.routes.salmonComposables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.powerdino.splatoon3_companion.R
import kotlin.text.Typography.bullet

@Composable
fun SalmonMapCard(
    mapName:String,
    mapImage:Int,
    weaponsList:List<String>,
    gearName:String,
    bigRun: Boolean
){
    var stateOfArrow by remember {
        mutableStateOf(false)
    }

    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(
            horizontal = 10.dp,
        ).clickable(
            onClick = {
                stateOfArrow = !stateOfArrow
            }
        ).animateContentSize()
        
    ){
        Column{
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
                modifier = Modifier.padding(12.dp)
            )


            if(stateOfArrow){
                Column(
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 1.dp
                    )
                ){
                    if(!bigRun) {
                        Row {
                            Text(
                                text = "Gear: ",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = gearName,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Text(
                        text = "Weapons:",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
                    Text(
                        text= buildAnnotatedString {
                            weaponsList.forEach {
                                withStyle(style = paragraphStyle) {
                                    append(bullet)
                                    append("\t\t")
                                    append(it)
                                }
                            }
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMapCard(){
    SalmonMapCard(
        mapName = stringResource(R.string.stage1),
        mapImage = R.drawable.stage1,
        weaponsList = listOf<String>(
            "Rebelion",
            "Splattershot",
            "Splattershot",
            "Splattershot",
        ),
        gearName = "Shirt",
        bigRun = false
    )
}