package com.powerdino.splatoon3_companion.widgets

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
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
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.widgets.composables.MapCardWidget
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


class SplatoonAppWidget : GlanceAppWidget() {

    override val sizeMode = SizeMode.Exact
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appRepository = DefaultAppContainer()
        val splatoonData = appRepository.splatoonRepository.getSplatoonData()
        val splatoonResources = appRepository.splatoonRepository.getVersusResources()

        provideContent {
            GlanceTheme {
                MyContent(
                    maps = splatoonData.normal,
                    resources = splatoonResources
                )
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    @Composable
    private fun MyContent(
        maps: List<Normal>,
        resources: ResourcesVersus
    ) {
        val context = LocalContext.current
        val size = LocalSize.current
        var currentWidth = 128.dp
        var currentHeight = 64.dp

        when(getWidgetSize()){
            WidgetSize.EXTRASMALL -> currentWidth = 32.dp
            WidgetSize.SMALL -> currentWidth = 48.dp
            WidgetSize.MEDIUM -> currentWidth= 128.dp
            WidgetSize.LARGE -> currentWidth = size.width /2  - 28.dp
        }

        Column(
            modifier = GlanceModifier.background(
                GlanceTheme.colors.widgetBackground
            )
        ) {
            Image(
                provider = ImageProvider(R.drawable.turfwar),
                contentDescription = "Paint",
                modifier = GlanceModifier
                    .size(32.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            maps.forEachIndexed { index, items ->
                if (index < 2) {

                    val secondInstant = Instant.parse(items.startTime).toLocalDateTime(TimeZone.currentSystemDefault())
                    var currentTime: String = secondInstant.toString()

                    val format12h = LocalDateTime.Format {
                        year();char('-');monthNumber();char('-');day();
                        char(' ')
                        amPmHour();char(':');minute();
                        char(' '); amPmMarker("AM", "PM")
                    }

                    if (!DateFormat.is24HourFormat(context)) {
                        currentTime = secondInstant.format(
                            format12h
                        )
                    }
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
                            ) {
                                Text(
                                    text = currentTime
                                        .substringAfterLast("T")
                                        .replace("T", " "),
                                    style = TextStyle(
                                        color = GlanceTheme.colors.onSurface,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                )
                            }
                            Row(
                                modifier = GlanceModifier
                                    .cornerRadius(6.dp)
                            ) {
                                items.regular.stages.forEach {
                                    Row {
                                        Box{
                                            MapCardWidget(
                                                mapName = resources.stages[it.toString()].toString(),
                                                mapImage = listOfMpMaps[it - 1].imageState,
                                                modifier = GlanceModifier
                                                    .wrapContentSize()
                                                    .size(
                                                        height = currentHeight,
                                                        width = currentWidth
                                                    )
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
    }
}
/*
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
*/
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

