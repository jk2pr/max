package com.hoppers.max

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.hoppers.max.localproviders.LocalNavController
import com.hoppers.max.presenter.ui.navigations.RootNavigationGraph
import com.hoppers.max.theme.MaxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val localNavHostController = rememberNavController()
            CompositionLocalProvider(
                LocalNavController provides localNavHostController,
            ) {
                MaxTheme {
                    RootNavigationGraph(localNavHostController)
                }

            }
        }
    }
}