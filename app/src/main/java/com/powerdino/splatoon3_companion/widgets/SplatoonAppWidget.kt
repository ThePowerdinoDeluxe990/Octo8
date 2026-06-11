package com.powerdino.splatoon3_companion.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.DefaultAppContainer
import com.powerdino.splatoon3_companion.data.lists.listOfMpMaps
import com.powerdino.splatoon3_companion.model.Bankara
import com.powerdino.splatoon3_companion.model.BankaraOpen
import com.powerdino.splatoon3_companion.model.League
import com.powerdino.splatoon3_companion.model.Normal
import com.powerdino.splatoon3_companion.model.Regular
import com.powerdino.splatoon3_companion.model.X
import com.powerdino.splatoon3_companion.widgets.composables.MapCardWidget
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


class SplatoonAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appRepository = DefaultAppContainer()
        val splatoonData = appRepository.splatoonRepository.getSplatoonData()

        provideContent {
            GlanceTheme{
                MyContent(
                    maps = splatoonData.normal
                )
            }
       }
    }

    // Very WIP I need to rewrite and redesign this
    @OptIn(ExperimentalTime::class)
    @Composable
    private fun MyContent(
        maps: List<Normal>
    ){

        Column (
            modifier = GlanceModifier.background(
                GlanceTheme.colors.widgetBackground
            )
        ){
            Image(
                provider = ImageProvider(R.drawable.turfwar),
                contentDescription = "Paint",
                modifier = GlanceModifier
                    .size(32.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            maps.forEachIndexed { index, items ->
                if(index <2){
                    val instant = Instant.parse(items.startTime)
                    val secondInstant = Instant.parse(items.endTime)
                    Column {
                        Row(
                            modifier = GlanceModifier.padding(
                                bottom = 3.dp
                            ).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
                                modifier = GlanceModifier.padding(
                                    horizontal = 6.dp,
                                ),
                            ){
                                Text(
                                    text = secondInstant.toString()
                                        .substringAfterLast("T")
                                        .replace("Z",""),
                                    style = TextStyle(
                                        color = GlanceTheme.colors.onSurface,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }

                            items.regular.stages.forEach {
                                Row {
                                    Box{
                                        MapCardWidget(
                                            androidx.glance.LocalContext.current.getString(
                                                listOfMpMaps[it - 1].nameState
                                            ),
                                            mapImage = listOfMpMaps[it - 1].imageState
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

  }

    @OptIn(ExperimentalGlancePreviewApi::class)
    @Composable
    @androidx.glance.preview.Preview
    @androidx.glance.preview.Preview(
        widthDp = 320,
        heightDp = 320,

    )
    private fun Preview(){
        GlanceTheme {
            MyContent(
                maps = previewList
            )
        }
    }
}

val previewList =
    listOf<Normal>(
        Normal(
            Bankara(
                rule = "paint",
                stages = listOf<Int>(6,7),
            ),
            BankaraOpen(
                rule = "paint",
                stages = listOf<Int>(6,7),
            ),
            endTime = "2025-12-20T10:00:00Z",
            League(
                eventId = "1",
                eventType = "paint",
                rule="paint",
                stages = listOf<Int>()
            ),
            phaseId = "1",
            Regular(
                rule = "paint",
                stages = listOf(6,7)
            ),
            startTime = "2025-12-20T10:00:00Z",
            x = X(
                rule = "paint",
                stages = listOf(6,7)
            )
        ),
        Normal(
            Bankara(
                rule = "paint",
                stages = listOf<Int>(7,8),
            ),
            BankaraOpen(
                rule = "paint",
                stages = listOf<Int>(7,8),
            ),
            endTime = "2025-12-20T11:00:00Z",
            League(
                eventId = "1",
                eventType = "paint",
                rule="paint",
                stages = listOf<Int>()
            ),
            phaseId = "1",
            Regular(
                rule = "paint",
                stages = listOf(7,8)
            ),
            startTime = "2025-12-20T11:00:00Z",
            x = X(
                rule = "paint",
                stages = listOf(7,8)
            )
        )
    )

