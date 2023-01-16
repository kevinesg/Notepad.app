package dev.kevinesg.notepadapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

open class GenericViewModel: ViewModel() {
    open val showBackButton = false
    open val title = ""
    open val showSave = false
    open val showDelete = false

    val showOptions = mutableStateOf(false)
    val showHelp = mutableStateOf(false)
    val showAbout = mutableStateOf(false)

    val showConfirmExitSave = mutableStateOf(false)
    val showConfirmDelete = mutableStateOf(false)
    val showConfirmExitApp = mutableStateOf(false)
    val showSaveSnackbar = mutableStateOf(false)

    open var frozenNoteUiState: NoteUiState? = null

    fun isActiveSave(uiState: NoteUiState): MutableState<Boolean> {
        return mutableStateOf(uiState.name.isNotBlank())
    }

    open suspend fun saveNote() {  }

    open suspend fun deleteNote() {  }
}


data class NoteUiState(
    val id: Int = 0,
    val name: String = "",
    val content: String = "",
    val updatedAt: String = ""
)