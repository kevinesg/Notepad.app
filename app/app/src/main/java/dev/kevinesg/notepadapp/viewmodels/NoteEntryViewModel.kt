package dev.kevinesg.notepadapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.kevinesg.notepadapp.data.Note
import dev.kevinesg.notepadapp.data.NotesRepository

class NoteEntryViewModel(private val notesRepository: NotesRepository): GenericViewModel() {

    override val showBackButton = true
    override val showSave = true

    var noteUiState by mutableStateOf(NoteUiState())
        private set

    fun updateUiState(newNoteUiState: NoteUiState) {
        noteUiState = newNoteUiState
    }

    override suspend fun saveNote() {
        if (isActiveSave(noteUiState).value) {
            notesRepository.insertNote(noteUiState.toNote())
        }
    }
}


fun NoteUiState.toNote(): Note = Note(
    id = id,
    name = name,
    content = content,
    updatedAt = updatedAt
)


fun Note.toNoteUiState(): NoteUiState = NoteUiState(
    id = id,
    name = name,
    content = content,
    updatedAt = updatedAt
)