package com.hoppers.max.presenter

sealed class Screen(val route: String) {
    sealed class AuthScreen(val route: String) {
        object Login : AuthScreen(route = "LOGIN")
    }
    object Home : Screen("home_screen")
    object Tabs {
        object Jobs : Screen("jobs_screen") // TAB1 Default
        object MyApplications : Screen("my_applications_screen") // TAB2
        object Search : Screen("search_screen") // TAB2
        object MyProfile : Screen("my_profile_screen") //TAB3
    }
    object JobDetail : Screen("job_detail_screen")
}