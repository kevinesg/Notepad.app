package dev.kevinesg.notepadapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dev.kevinesg.notepadapp.data.NotesRepository
import dev.kevinesg.notepadapp.ui.screens.NoteEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NoteEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
): GenericViewModel() {

    override val showBackButton = true
    override val showSave = true
    override val showDelete = true

    var noteUiState by mutableStateOf(NoteUiState())
        private set

    private val noteId: Int = checkNotNull(savedStateHandle[NoteEditDestination.noteIdArg])

    init {
        viewModelScope.launch {
            noteUiState = notesRepository.getNoteStream(noteId)
                .filterNotNull()
                .first()
                .toNoteUiState()

            val snapshot = Snapshot.takeSnapshot()
            if (frozenNoteUiState == null) {
                frozenNoteUiState = snapshot.enter { noteUiState }
            }
        }
    }


    fun updateUiState(newNoteUiState: NoteUiState) {
        noteUiState = newNoteUiState
    }

    override suspend fun saveNote() {
        if (isActiveSave(noteUiState).value) {
            notesRepository.updateNote(noteUiState.toNote())
        }
    }

    override suspend fun deleteNote() {
        notesRepository.deleteNote(noteUiState.toNote())
    }
}