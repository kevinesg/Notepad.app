package dev.kevinesg.notepadapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.kevinesg.notepadapp.Screens
import dev.kevinesg.notepadapp.viewmodels.GenericViewModel
import dev.kevinesg.notepadapp.viewmodels.NoteUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EditNote(
    viewModel: GenericViewModel,
    navController: NavController,
    onNavigateBack: () -> Unit,
    frozenNoteUiState: NoteUiState,
    noteUiState: NoteUiState,
    modifier: Modifier = Modifier,
    onValueChange: (NoteUiState) -> Unit = {  },
    navigateBackAfterSave: Boolean = true
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF000000))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            viewModel = viewModel,
            onBackClick = onNavigateBack,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveNote()
                    viewModel.showSaveSnackbar.value = true
                    delay(1500)
                    viewModel.showSaveSnackbar.value = false
                    }
                if (navigateBackAfterSave) {
                    navController.navigate(Screens.HOME.name) {
                        popUpTo(0)
                    }
                }
            },
            isSaveEnabled = frozenNoteUiState != noteUiState && noteUiState.name != ""
        )

        Column(modifier = Modifier.weight(0.8f)) {
            TextField(
                value = noteUiState.name,
                onValueChange = { onValueChange(noteUiState.copy(name = it)) },
                label = { Text(text = "Title *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = true,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFf4ddc6),
                    textColor = Color(0xFF000000),
                    focusedLabelColor = Color(0xFF000000),
                    cursorColor = Color(0xFF000000),
                    focusedIndicatorColor = Color(0xFF000000),
                    unfocusedLabelColor = Color(0xFF000000)
                )
            )
            TextField(
                value = noteUiState.content,
                onValueChange = { onValueChange(noteUiState.copy(content = it)) },
                label = { Text("Note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(0.1F),
                shape = RoundedCornerShape(12.dp),
                enabled = true,
                singleLine = false,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFf4ddc6),
                    textColor = Color(0xFF000000),
                    focusedLabelColor = Color(0xFF000000),
                    cursorColor = Color(0xFF000000),
                    focusedIndicatorColor = Color(0xFF000000),
                    unfocusedLabelColor = Color(0xFF000000)
                )
            )
        }
    }
    BackHandler { onNavigateBack() }
}


