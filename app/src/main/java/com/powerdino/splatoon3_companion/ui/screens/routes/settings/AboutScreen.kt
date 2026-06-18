package com.powerdino.splatoon3_companion.ui.screens.routes.settings

import android.content.pm.PackageManager
import android.graphics.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.createBitmap
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.ui.screens.about_screen_data.linkList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AboutScreen(
    onClickBack:()-> Unit
){
    val context = LocalContext.current
    var appVersionName = ""
    var appVersionCode = ""
    val uriHandler = LocalUriHandler.current

    try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val appVersion = packageInfo.versionName
        val appCode = packageInfo.versionCode.toString()

        appVersionName = "Version Name: $appVersion"
        appVersionCode = "Version Code: $appCode"
    } catch (_: PackageManager.NameNotFoundException) { }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick =onClickBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack ,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = { Text("About") },
            )
        }
    ) { innerPadding->
        ResourcesCompat.getDrawable(
            LocalContext.current.resources,
            R.mipmap.ic_launcher, LocalContext.current.theme
        )?.let { drawable ->
            val bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){

                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = stringResource(R.string.app_name),
                    )

                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(12.dp)
                    )

                    Text(
                        text = appVersionName,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(3.dp)
                    )

                    Text(
                        text = appVersionCode,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(3.dp)
                    )
                }

                Column {
                    linkList.forEach { item ->
                        val link = stringResource(item.subtitle)
                        ListItem(
                            modifier = Modifier.clickable(onClick = {
                                uriHandler.openUri(link)
                            }),
                            headlineContent = {
                                Text(stringResource(item.title)) },
                            supportingContent = {
                                Text(stringResource(item.subtitle)) },
                        )
                    }
                }
            }
        }
    }
}



@Composable
@Preview(showSystemUi = true, showBackground = false)
private fun Preview(){
   AboutScreen({})
}