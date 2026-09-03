package com.powerdino.splatoon3_companion.widgets

import android.content.Context
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
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.data.DefaultAppContainer
import com.powerdino.splatoon3_companion.model.AdditionalProp1
import com.powerdino.splatoon3_companion.model.Bankara
import com.powerdino.splatoon3_companion.model.BankaraOpen
import com.powerdino.splatoon3_companion.model.League
import com.powerdino.splatoon3_companion.model.Normal
import com.powerdino.splatoon3_companion.model.Regular
import com.powerdino.splatoon3_companion.model.X
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.widgets.utils.getWidgetSize
import kotlin.time.ExperimentalTime

class SplatoonAppWidget : GlanceAppWidget() {

    override val sizeMode = SizeMode.Exact
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appRepository = DefaultAppContainer()
        val splatoonData = appRepository.splatoonRepository.getSplatoonData()
        val splatoonResources = appRepository.splatoonRepository.getVersusResources()

        provideContent {
            GlanceTheme {
                MyContent(
                    festMaps = splatoonData.fest,
                    maps = splatoonData.normal,
                    resources = splatoonResources
                )
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    @Composable
    private fun MyContent(
        festMaps: Map<String, List<AdditionalProp1>>?,
        maps: List<Normal>,
        resources: ResourcesVersus
    ) {
        val context = LocalContext.current
        val size = LocalSize.current
        var currentWidth = 128.dp
        val currentHeight = 64.dp

        currentWidth = getWidgetSize(size.width)

        Column(
            modifier = GlanceModifier.background(
                GlanceTheme.colors.widgetBackground
            )
        ) {
            Row {
                Image(
                    provider = ImageProvider(R.drawable.turfwar),
                    contentDescription = "Paint",
                    modifier = GlanceModifier
                        .size(32.dp)
                        .padding(4.dp)
                )
            }

            if(festMaps.isNullOrEmpty()){
                WidgetRegular(
                    maps,
                    width = currentWidth,
                    height = currentHeight,
                    resources = resources,
                    context = context
                )
            }else{
                Text("Splatfest twin")
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

