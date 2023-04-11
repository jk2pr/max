package com.hoppers.max.presenter.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.hoppers.max.presenter.Screen

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)
val bottomNavItems = listOf(
    BottomNavItem(
        name = "Jobs",
        route = Screen.Tabs.Jobs.route,
        icon = Icons.Rounded.Home,
    ),
    BottomNavItem(
        name = "My Applications",
        route = Screen.Tabs.MyApplications.route,
        icon = Icons.Rounded.AddCircle,
    ),
    BottomNavItem(
        name = "Search",
        route = Screen.Tabs.Search.route,
        icon = Icons.Rounded.AddCircle,
    ),
    BottomNavItem(
        name = "My Profile",
        route = Screen.Tabs.MyProfile.route,
        icon = Icons.Rounded.Settings,
    ),
)