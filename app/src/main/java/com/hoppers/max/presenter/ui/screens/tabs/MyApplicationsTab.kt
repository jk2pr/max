package com.hoppers.max.presenter.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hoppers.max.presenter.ui.component.Page

@Composable
fun MyApplicationsTab() {
    Page(pageTitle = "My Application") {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("My Application section will be coming soon")
        }
    }
}