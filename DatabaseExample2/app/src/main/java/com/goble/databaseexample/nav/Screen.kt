package com.goble.databaseexample.nav

sealed class Screen(val route:String) {
    object Home:Screen("Home")

    object Add:Screen("Add")
}