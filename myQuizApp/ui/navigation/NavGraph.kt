package com.example.quizdatabaseapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizdatabaseapp.ui.home.HomeScreen
import com.example.quizdatabaseapp.ui.question.QuestionEntryScreen
import com.example.quizdatabaseapp.ui.question.QuestionEntryViewModel

@Composable
fun QuizNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: QuestionEntryViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onStartQuiz = { category ->

                },
                navigateToQuestionEntry = {
                    navController.navigate(Screen.Entry.route)
                }
            )
        }

        composable(Screen.Entry.route) {
            QuestionEntryScreen(
                navigateBack = { navController.popBackStack()},
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
    }
}