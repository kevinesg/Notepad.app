package dev.kevinesg.notepadapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.kevinesg.notepadapp.ui.screens.HomeScreen
import dev.kevinesg.notepadapp.ui.screens.NoteEditDestination
import dev.kevinesg.notepadapp.ui.screens.NoteEditScreen
import dev.kevinesg.notepadapp.ui.screens.NoteEntryScreen


@Composable
fun Main(
    navController: NavHostController = rememberNavController()
) {
    Box {
        NavHost(
            navController = navController,
            startDestination = Screens.HOME.name
        ) {
            composable(route = Screens.HOME.name) {
                HomeScreen(
                    navigateToNoteEntry= {
                        navController.navigate(Screens.ADD_NOTE.name) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier,
                    onNoteClick = {
                        navController.navigate("${NoteEditDestination.route}/${it.id}")
                    }
                )
            }

            composable(route = Screens.ADD_NOTE.name) {
                NoteEntryScreen(navController = navController)
            }

            composable(
                route = NoteEditDestination.routeWithArgs,
                arguments = listOf(navArgument(NoteEditDestination.noteIdArg) {
                    type = NavType.IntType
                })
            ) {
                NoteEditScreen(navController = navController)
            }
        }
    }
}


enum class Screens {
    HOME,
    ADD_NOTE
}

interface EditNoteDestination {
    val route: String
    val titleRes: String
}