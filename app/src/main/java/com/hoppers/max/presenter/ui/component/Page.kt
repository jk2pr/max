package com.hoppers.max.presenter.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.hoppers.max.R
import com.hoppers.max.localproviders.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page(
    pageTitle: String = "",
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val localNavController = LocalNavController.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = { if (pageTitle.isNotBlank()) Text(text = pageTitle, maxLines = 1) },
                navigationIcon = {
                    if (localNavController.previousBackStackEntry != null)
                        IconButton(onClick = { localNavController.popBackStack() }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                                colorFilter = ColorFilter.tint(LocalContentColor.current),
                                contentDescription = "back"
                            )
                        }
                },
            )
        },
        bottomBar = bottomBar,
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                color = MaterialTheme.colorScheme.background,
                content = content
            )

        }
    )
}


