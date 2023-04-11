package com.hoppers.max.presenter.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hoppers.max.presenter.ui.screens.HomeScreen
import com.hoppers.max.presenter.ui.screens.JobDetailScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph()
        composable(route = Graph.HOME) {
            HomeScreen()
        }
        composable(route = Graph.DETAILS) {
            JobDetailScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}