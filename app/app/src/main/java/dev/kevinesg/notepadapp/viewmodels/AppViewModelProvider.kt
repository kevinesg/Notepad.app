package dev.kevinesg.notepadapp.viewmodels



import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.kevinesg.notepadapp.NoteApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(
                noteApplication().container.notesRepository
            )
        }

        initializer {
            NoteEntryViewModel(
                noteApplication().container.notesRepository
            )
        }

        initializer {
            NoteEditViewModel(
                this.createSavedStateHandle(),
                noteApplication().container.notesRepository
            )
        }
    }
}


fun CreationExtras.noteApplication(): NoteApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NoteApplication)