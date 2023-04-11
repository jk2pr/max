package com.hoppers.max.presenter.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hoppers.max.presenter.ui.component.Page

@Composable
fun SearchTab() {
    Page(pageTitle = "Search") {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Search section will be coming soon")
        }
    }
}