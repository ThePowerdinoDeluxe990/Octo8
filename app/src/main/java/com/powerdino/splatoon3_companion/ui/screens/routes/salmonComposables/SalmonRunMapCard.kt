package com.powerdino.splatoon3_companion.ui.screens.routes.salmonComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.lists.SalmonRunWeapons

@Composable
fun SalmonMapCard(
    mapName: String,
    mapImage: Int,
    weaponsList: List<Int?>,
    gearName: String,
    event: Boolean
){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(
            horizontal = 4.dp,
        )
    ){
        Column{
            Image(
                painter = painterResource(mapImage),
                contentDescription = mapName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .size(128.dp)
            )

            Column {
                Text(
                    text=mapName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 3.dp,
                        start = 4.dp,
                        end = 12.dp
                    )
                )
                Row(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    weaponsList.forEach { e ->
                        SalmonRunWeapons.forEach { element ->
                            element.id.forEach { id ->
                                if (e == id) {
                                    AsyncImage(
                                        contentScale = ContentScale.Crop,
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(element.image)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = e.toString(),
                                        modifier = Modifier
                                            .padding(bottom = 2.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .size(40.dp)
                                            .background(
                                                color = MaterialTheme.colorScheme.surfaceContainer
                                            )
                                    )

                                }
                            }
                        }
                        if(e == -1){
                            Image(
                                painterResource(R.drawable.coop_random),
                                "Unknown",
                                Modifier
                                    .padding(bottom = 2.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .size(40.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceContainer
                                    )
                            )
                        }else if(e==-2){
                            Image(
                                painterResource(R.drawable.coop_random_gold),
                                "Golden Rotation",
                                Modifier
                                    .padding(bottom = 2.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .size(40.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceContainer
                                    )
                            )
                        }

                    }
                }
            }


            Column(
                modifier = Modifier.padding(
                    horizontal = 4.dp,
                    vertical = 1.dp
                )
            ){
                if(!event) {
                    Row {
                        Text(
                            text = stringResource(R.string.gear)+": ",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        SelectionContainer {
                            Text(
                                text = gearName,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMapCard(){
    SalmonMapCard(
        mapName ="Map1",
        mapImage = R.drawable.stage1,
        weaponsList = listOf(
            30,
            30,
            30,
            30,
        ),
        gearName = "Shirt",
        event = false
    )
}