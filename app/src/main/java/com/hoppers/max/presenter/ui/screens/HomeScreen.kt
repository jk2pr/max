package com.hoppers.max.presenter.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.hoppers.max.localproviders.LocalNavController
import com.hoppers.max.presenter.ui.component.Page
import com.hoppers.max.presenter.ui.navigations.BottomNavigation
import com.hoppers.max.presenter.ui.navigations.HomeNavGraph

@Composable
fun HomeScreen() {
    val localNavHostController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides localNavHostController,
    ) {
        Page(
            bottomBar = { BottomNavigation() },
            content = { HomeNavGraph(navController = localNavHostController) })
    }
}

