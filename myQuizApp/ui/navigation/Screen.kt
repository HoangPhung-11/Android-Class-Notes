package com.example.quizdatabaseapp.ui.navigation

sealed class Screen(val route:String) {
    object Home:Screen("home")
    object Entry:Screen("entry")
}