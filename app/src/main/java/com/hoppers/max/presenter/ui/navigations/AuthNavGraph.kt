package com.hoppers.max.presenter.ui.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hoppers.max.presenter.Screen
import com.hoppers.max.presenter.ui.screens.LoginScreen

fun NavGraphBuilder.authNavGraph() {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screen.AuthScreen.Login.route
    ) {
        composable(route = Screen.AuthScreen.Login.route) {
            LoginScreen()
        }
    }
}

