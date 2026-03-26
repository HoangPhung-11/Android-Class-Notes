package com.goble.databaseexample.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goble.databaseexample.data.ContactEvent
import com.goble.databaseexample.data.ContactState
import com.goble.databaseexample.views.AddContactScreen
import com.goble.databaseexample.views.ContactScreen

@Composable
fun ContactNavGraph(
    navController: NavHostController = rememberNavController(),
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            ContactScreen(
                state = state,
                onEvent = onEvent,
                addContactNavigation = {
                    navController.navigate(Screen.Add.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable(route = Screen.Add.route) {
            AddContactScreen(
                state = state,
                onEvent = onEvent,
                onSubmission = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Add.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }
    }
}