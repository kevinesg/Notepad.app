package dev.kevinesg.notepadapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.kevinesg.notepadapp.EditNoteDestination
import dev.kevinesg.notepadapp.Screens
import dev.kevinesg.notepadapp.viewmodels.AppViewModelProvider
import dev.kevinesg.notepadapp.viewmodels.NoteEditViewModel
import dev.kevinesg.notepadapp.viewmodels.NoteUiState


object NoteEditDestination: EditNoteDestination {
    override val route = "note_edit"
    override val titleRes = "Edit Note"
    const val noteIdArg = "noteId"
    val routeWithArgs = "$route/{$noteIdArg}"
}


@Composable
fun NoteEditScreen(
    viewModel: NoteEditViewModel = viewModel(factory = AppViewModelProvider.Factory),
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
                //frozenNoteUiState = viewModel.frozenNoteUiState?: NoteUiState(),
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

    if (viewModel.showConfirmDelete.value) {
        ConfirmDelete(
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