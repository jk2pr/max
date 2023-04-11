package com.hoppers.max.presenter.ui.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hoppers.max.presenter.Screen
import com.hoppers.max.presenter.ui.screens.HomeScreen
import com.hoppers.max.presenter.ui.screens.JobDetailScreen
import com.hoppers.max.presenter.ui.screens.tabs.JobsScreenTab
import com.hoppers.max.presenter.ui.screens.tabs.MyApplicationsTab
import com.hoppers.max.presenter.ui.screens.tabs.MyProfileTab
import com.hoppers.max.presenter.ui.screens.tabs.SearchTab

@Composable
fun HomeNavGraph(navController:NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Tabs.Jobs.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        //Tabs
        composable(route = Screen.Tabs.MyProfile.route) {
            MyProfileTab()
        }
        composable(route = Screen.Tabs.MyApplications.route) {
            MyApplicationsTab()
        }
        composable(route = Screen.Tabs.Search.route) {
            SearchTab()
        }
        composable(route = Screen.Tabs.Jobs.route) {
            JobsScreenTab()
        }
        //End of Tab
        detailsNavGraph()
    }
}
fun NavGraphBuilder.detailsNavGraph() {

    navigation(
        route = Graph.DETAILS,
        startDestination = Screen.JobDetail.route
    ) {
        composable(route = Screen.JobDetail.route) {
            JobDetailScreen()
        }

    }
}