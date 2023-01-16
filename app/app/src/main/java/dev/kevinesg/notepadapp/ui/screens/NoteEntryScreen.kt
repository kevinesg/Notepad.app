package dev.kevinesg.notepadapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.kevinesg.notepadapp.Screens
import dev.kevinesg.notepadapp.viewmodels.AppViewModelProvider
import dev.kevinesg.notepadapp.viewmodels.NoteEntryViewModel
import dev.kevinesg.notepadapp.viewmodels.NoteUiState


@Composable
fun NoteEntryScreen(
    viewModel: NoteEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController
) {
    val onNavigateBack = {
        if (viewModel.noteUiState != viewModel.frozenNoteUiState && viewModel.noteUiState.name != "") {
            viewModel.showConfirmExitSave.value = true
        } else {
            navController.navigate(Screens.HOME.name) { popUpTo(0) }
        }
    }

    Box {
        Column {
            EditNote(
                viewModel = viewModel,
                navController = navController,
                //frozenNoteUiState = NoteUiState(),
                noteUiState = viewModel.noteUiState,
                onValueChange = viewModel::updateUiState,
                onNavigateBack = onNavigateBack,
                navigateBackAfterSave = false
            )
        }
    }
    if (viewModel.showHelp.value) {
        Help(viewModel = viewModel)
    }

    if (viewModel.showAbout.value) {
        About(viewModel = viewModel)
    }

    if (viewModel.showConfirmExitSave.value) {
        ConfirmExitSave(
            viewModel = viewModel,
            onNavigateBack = {
                navController.navigate(Screens.HOME.name) {
                    popUpTo(0)
                }
            }
        )
    }

    if (viewModel.showSaveSnackbar.value) {
        SaveSnackbar()
    }

    BackHandler { onNavigateBack() }
}