package com.powerdino.splatoon3_companion.ui.screens.routes.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mikepenz.aboutlibraries.ui.compose.android.produceLibraries
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.powerdino.splatoon3_companion.R

@Composable
fun Libraries(
    onClickBack:() -> Unit
){
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
                title = { Text(stringResource(R.string.menu_about)) },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            val libraries by produceLibraries(R.raw.aboutlibraries)
            LibrariesContainer(libraries, Modifier.fillMaxSize())
        }
    }
}