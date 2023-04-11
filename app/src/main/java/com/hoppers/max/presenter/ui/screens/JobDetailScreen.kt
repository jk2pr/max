package com.hoppers.max.presenter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hoppers.max.MainActivity
import com.hoppers.max.constants.KEY_ITEM_SELECTED
import com.hoppers.max.domain.Job
import com.hoppers.max.extensions.showToast
import com.hoppers.max.localproviders.LocalNavController
import com.hoppers.max.presenter.ui.component.Page

@Composable
fun JobDetailScreen() {

    val job =
        LocalNavController.current
            .previousBackStackEntry?.savedStateHandle?.get<Job>(KEY_ITEM_SELECTED)
    job?.let {
        val localContext = LocalContext.current as MainActivity
        Page(pageTitle = job.positionTitle.orEmpty()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = job.positionTitle.orEmpty(),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = job.description.orEmpty())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(onClick = { localContext.showToast("Coming Soon") }) {
                    Text(text = "Apply")
                }
            }
        }
    }


}