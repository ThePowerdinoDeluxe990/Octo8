package com.powerdino.splatoon3_companion.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MenuAnchorPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.powerdino.splatoon3_companion.R
import com.powerdino.splatoon3_companion.ui.screens.routes.Aboutscreen
import com.powerdino.splatoon3_companion.ui.screens.routes.settings.ScreensSettings
import com.powerdino.splatoon3_companion.ui.viewModels.SettingsViewModel
import kotlinx.coroutines.flow.StateFlow

val screensOptions = listOf<Int>(
    R.string.regular,
    R.string.comp,
    R.string.salmon_run,
    R.string.event
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    onClickBack: () -> Unit,
    backStack: NavBackStack<NavKey>,
    settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModel.Factory
    )

) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("12h") }

    val uiState = settingsViewModel.uiState.collectAsState().value
    val selectDefaultScreen = settingsViewModel::selectDefaultScreen
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onClickBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = { Text(stringResource(R.string.settings_menu)) },
            )
        }
    ) { innerPadding ->

        Column(
            Modifier.padding(innerPadding)
        ) {
            ListItem(
                modifier = Modifier.clickable(onClick = {
                    expanded = true
                }),
                leadingContent = null,
                trailingContent = null,
                overlineContent = null,
                supportingContent = null,
                colors = ListItemDefaults.colors(),
                elevation = ListItemDefaults.elevation(ListItemDefaults.Elevation),
                content = {
                    Text("Change default screen")
                },
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

                TextField(
                    value = uiState.screenLocation.toString(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    screensOptions.forEachIndexed { index, i ->
                        DropdownMenuItem(
                            text = { Text(stringResource(i)) },
                            onClick = {
                                selectDefaultScreen(index)
                                expanded = false
                            },
                        )
                    }
                }
                }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ListItem(
                    modifier = Modifier.clickable(
                        onClick = {
                            val intent = Intent(Settings.ACTION_APP_LOCALE_SETTINGS)
                            val uri = Uri.fromParts("package", context.packageName, null)
                            intent.data = uri

                            context.startActivity(intent)

                        }),
                    leadingContent = null,
                    trailingContent = null,
                    overlineContent = null,
                    supportingContent = {
                        Text(stringResource(R.string.settings_lang_sub))
                    },
                    colors = ListItemDefaults.colors(),
                    elevation = ListItemDefaults.elevation(ListItemDefaults.Elevation),
                    content = {
                                        Text(stringResource(R.string.settings_lang))
                                    },
                )
            }

            ListItem(
                modifier = Modifier.clickable(onClick = {
                                backStack.add(Aboutscreen)
                            }),
                leadingContent = null,
                trailingContent = null,
                overlineContent = null,
                supportingContent = null,
                colors = ListItemDefaults.colors(),
                elevation = ListItemDefaults.elevation(ListItemDefaults.Elevation),
                content = {
                                Text(stringResource(R.string.menu_about))
                            },
            )
        }
    }
}