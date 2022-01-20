package com.github.sgeorgiev.randompoems.ui

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
}
