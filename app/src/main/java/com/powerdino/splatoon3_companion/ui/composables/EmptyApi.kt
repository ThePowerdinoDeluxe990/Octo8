package com.powerdino.splatoon3_companion.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.powerdino.splatoon3_companion.R

@Composable
fun EmptyApi(
    message: String
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.nostage),
            contentDescription = "No Stage?"
        )

        Text(
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            text = message,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun Preview(){
    EmptyApi("Currently, there are no Splatfests available")
}