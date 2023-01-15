package dev.kevinesg.notepadapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.kevinesg.notepadapp.R
import dev.kevinesg.notepadapp.data.Note
import dev.kevinesg.notepadapp.viewmodels.AppViewModelProvider
import dev.kevinesg.notepadapp.viewmodels.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToNoteEntry: () -> Unit,
    onNoteClick: (Note) -> Unit,
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color(0xFF000000))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                viewModel = viewModel,
                isSaveEnabled = false
            )
            Column(modifier = Modifier.weight(0.8f)) {
                NotesList(
                    notesList = homeUiState.noteList,
                    onNoteClick = onNoteClick
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.2f),
                verticalArrangement = Arrangement.Bottom
            ) {
                AddNoteButton(
                    navigateToNoteEntry = navigateToNoteEntry
                )
            }
        }

        if (viewModel.showHelp.value) {
            Help(viewModel = viewModel)
        }

        if (viewModel.showAbout.value) {
            About(viewModel = viewModel)
        }

        if (viewModel.showConfirmExitApp.value) {
            ConfirmExitApp(viewModel = viewModel)
        }
    }

    BackHandler { viewModel.showConfirmExitApp.value = true }
}


@Composable
fun NotesList(
    notesList: List<Note>,
    onNoteClick: (Note) -> Unit
) {
    LazyColumn {
        items(notesList) { note ->
            NoteCard(
                note = note,
                onNoteClick = onNoteClick
            )
        }
    }
}


@Composable
fun NoteCard(
    note: Note,
    onNoteClick: (Note) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onNoteClick(note)
            },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFf4ddc6))
                .padding(8.dp)
                .height(64.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = note.name, color = Color(0xFF000000), fontSize = 16.sp)
        }
    }
}


@Composable
fun AddNoteButton(
    navigateToNoteEntry: () -> Unit
) {
    Button(
        onClick = navigateToNoteEntry,
        modifier = Modifier
            .fillMaxWidth(0.67f)
            .height(50.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFffffff), contentColor = Color(0xFF000000)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        val myId = "inlineContent"
        val text = buildAnnotatedString {
            appendInlineContent(myId, "[icon]")
            append(" CREATE")
        }

        val inlineContent = mapOf(
            Pair(
                myId,
                InlineTextContent(
                    Placeholder(
                        width = 24.sp,
                        height = 24.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_notes_icon),
                        contentDescription = "add notes icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        )
        Text(text = text, inlineContent = inlineContent, fontSize = 24.sp)
    }
}















