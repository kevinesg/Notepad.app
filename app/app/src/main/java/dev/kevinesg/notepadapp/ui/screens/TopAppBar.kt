package dev.kevinesg.notepadapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.kevinesg.notepadapp.R
import dev.kevinesg.notepadapp.viewmodels.GenericViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TopAppBar(
    viewModel: GenericViewModel,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {  },
    onSaveClick: () -> Unit = {  },
    isSaveEnabled: Boolean,
    backgroundColor: Color = Color(0xFF000000),
    contentColor: Color = Color(0xFFf4ddc6)
) {
    val focusManager = LocalFocusManager.current

    TopAppBar(
        navigationIcon = {
            if (viewModel.showBackButton) {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    onBackClick()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "arrow back"
                    )
                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.notepad_icon),
                    contentDescription = "app icon"
                )
            }
        },
        title = { Text(viewModel.title) },
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,

        actions = {
            if (viewModel.showSave) {
                IconButton(
                    modifier = Modifier.height(48.dp),
                    enabled = isSaveEnabled,
                    onClick = {
                        focusManager.clearFocus()
                        onSaveClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.save_icon),
                        contentDescription = "save icon",
                        tint = if (isSaveEnabled) {
                            contentColor
                        } else {
                            Color.Gray
                        }
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))
            }

            if (viewModel.showDelete) {
                IconButton(
                    modifier = Modifier.height(48.dp),
                    onClick = { viewModel.showConfirmDelete.value = true }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = "delete icon",
                        tint = contentColor
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))
            }

            Box {
                IconButton(
                    modifier = Modifier.height(48.dp),
                    onClick = { viewModel.showOptions.value = !viewModel.showOptions.value }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.options_icon),
                        contentDescription = "options menu",
                        tint = contentColor
                    )
                }
                DropdownMenu(
                    modifier = Modifier.background(color = Color(0xFFffffff)),
                    expanded = viewModel.showOptions.value,
                    onDismissRequest = {
                        viewModel.showOptions.value = !viewModel.showOptions.value
                    }
                ) {
                    DropdownMenuItem(onClick = {
                        viewModel.showHelp.value = true
                        viewModel.showOptions.value = false
                    }) {
                        Text(text = "Help", color = Color(0xFF000000))
                    }
                    DropdownMenuItem(onClick = {
                        viewModel.showAbout.value = true
                        viewModel.showOptions.value = false
                    }) {
                        Text(text = "About", color = Color(0xFF000000))
                    }
                }
            }
        }
    )
}