package dev.kevinesg.notepadapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import dev.kevinesg.notepadapp.R
import dev.kevinesg.notepadapp.viewmodels.GenericViewModel
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@Composable
fun Help(viewModel: GenericViewModel) {
    Surface (
        color = Color.Black.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxSize()
    ) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { viewModel.showHelp.value = false }
        ) {
            Column(
                modifier = Modifier
                    .size(300.dp, 350.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(20.dp)
                    .clickable { viewModel.showHelp.value = false }
            ) {
                Text(
                    text = "How to use",
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    color = Color(0xFF000000),
                    text = AnnotatedString(text = "    Click the CREATE button to add a new note.",
                        spanStyles = listOf(
                            AnnotatedString.Range(
                                SpanStyle(fontWeight = FontWeight.Bold), start = 14, end = 20
                            ))
                    )
                )
                Text(
                    color = Color(0xFF000000),
                    text = "    Click on a note card to view and/or update"
                )

                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.save_icon),
                        contentDescription = null,
                        modifier = Modifier,
                        colorResource(id = R.color.black)
                    )
                    Text(color = Color(0xFF000000), text = " to save/update a note")
                }
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = null,
                        modifier = Modifier,
                        colorResource(id = R.color.black)
                    )
                    Text(color = Color(0xFF000000), text = " to delete a note")
                }
            }
            BackHandler {
                if (viewModel.showHelp.value) {
                    viewModel.showHelp.value = false
                }
            }
        }
    }
}


@Composable
fun About(viewModel: GenericViewModel) {
    val context = LocalContext.current
    val intentGithub = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kevinesg/Notepad.app")) }
    val intentLinkedin = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/kevinesg/")) }
    val intentPaypal = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/paypalme/kevinesg")) }
    Surface (
        color = Color.Black.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxSize()
    ) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { viewModel.showAbout.value = false }
        ) {
            Column(
                modifier = Modifier
                    .size(300.dp, 400.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(20.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "About",
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "  Store your notes easily with Notepad.app! ☺ It is a lightweight, open-source app-- the complete source code can be found in the Github link below.",
                    color = Color(0xFF000000),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "  No data goes out of your phone as everything is stored locally. This is built primarily as a hobby and out of interest :)",
                    color = Color(0xFF000000),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "  It is free and ad-free, but donations are welcome.",
                    color = Color(0xFF000000),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.github_icon),
                        contentDescription = "Github icon",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { context.startActivity(intentGithub) },
                        colorResource(id = R.color.black)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.linkedin_icon),
                        contentDescription = "Linkedin icon",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { context.startActivity(intentLinkedin) }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.paypal_icon),
                        contentDescription = "Paypal icon",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { context.startActivity(intentPaypal) }
                    )
                }
            }
            BackHandler {
                if (viewModel.showAbout.value) {
                    viewModel.showAbout.value = false
                }
            }
        }
    }
}


@Composable
fun ConfirmExitSave(
    viewModel: GenericViewModel,
    onNavigateBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Surface (
        color = Color.Black.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxSize()
    ) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { viewModel.showConfirmExitSave.value = false }
        ) {
            Column(
                modifier = Modifier
                    .size(300.dp, 150.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(20.dp)
            ) {
                Text(
                    text = "Save changes?",
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.weight(1f))

                Row {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                       text = "DISCARD",
                       fontWeight = FontWeight.Bold,
                       color = Color(0xFF000000),
                       modifier = Modifier.clickable { 
                           viewModel.showConfirmExitSave.value = false
                           onNavigateBack()
                       }
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Row(horizontalArrangement = Arrangement.End) {
                        Text(
                            text = "SAVE",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    viewModel.saveNote()
                                    viewModel.showConfirmExitSave.value = false
                                    onNavigateBack()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ConfirmDelete(
    viewModel: GenericViewModel,
    onNavigateBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Surface (
        color = Color.Black.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxSize()
    ) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { viewModel.showConfirmDelete.value = false }
        ) {
            Column(
                modifier = Modifier
                    .size(300.dp, 150.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(20.dp)
            ) {
                Text(
                    text = "Delete note?",
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.weight(1f))

                Row {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "NO",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                        modifier = Modifier.clickable {
                            viewModel.showConfirmDelete.value = false
                        }
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Row(horizontalArrangement = Arrangement.End) {
                        Text(
                            text = "YES",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    viewModel.deleteNote()
                                    viewModel.showConfirmDelete.value = false
                                    onNavigateBack()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

/*
@Composable
fun NoteSavedNotification(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    action
) {

}*/


@Composable
fun ConfirmExitApp(
    viewModel: GenericViewModel
) {
    Surface (
        color = Color.Black.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxSize()
    ) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { viewModel.showConfirmExitApp.value = false }
        ) {
            Column(
                modifier = Modifier
                    .size(300.dp, 150.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(20.dp)
            ) {
                Text(
                    text = "Are you sure you want to exit?",
                    color = Color(0xFF000000),
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.weight(1f))

                Row {
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "NO",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                        modifier = Modifier.clickable {
                            viewModel.showConfirmExitApp.value = false
                        }
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Row(horizontalArrangement = Arrangement.End) {
                        Text(
                            text = "YES",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            modifier = Modifier.clickable { exitProcess(0) }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SaveSnackbar(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Snackbar(
            modifier = Modifier.fillMaxWidth(0.67f),
            backgroundColor = Color(0xFF000000),
            contentColor = Color(0xFFffffff)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Note saved")
            }
        }
    }
}


/*
@Composable
fun TestSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        scope.launch { snackbarHostState.showSnackbar("Test", duration = SnackbarDuration.Short) }

    )
}
*/