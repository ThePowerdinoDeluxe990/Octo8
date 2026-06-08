package com.powerdino.splatoon3_companion.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powerdino.splatoon3_companion.R

@Composable
fun ErrorScreen(
    tryAgain:() -> Unit
){

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(R.drawable.noconnectionicon),
                contentDescription = "Network Error"
            )


            Text(
                text = stringResource(R.string.Error),
                style = MaterialTheme.typography.titleLarge
            )

            Button(
                onClick = tryAgain
            ){
                Text("Try Again")
            }
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
@Preview
@Composable
fun PreviewErrorScreen(){
    ErrorScreen({})
}