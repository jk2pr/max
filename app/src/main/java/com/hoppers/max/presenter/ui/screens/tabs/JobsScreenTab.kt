package com.hoppers.max.presenter.ui.screens.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hoppers.max.constants.KEY_ITEM_SELECTED
import com.hoppers.max.data.UiState
import com.hoppers.max.domain.Job
import com.hoppers.max.domain.JobResponse
import com.hoppers.max.domain.SalaryRange
import com.hoppers.max.localproviders.LocalNavController
import com.hoppers.max.presenter.ui.component.Error
import com.hoppers.max.presenter.ui.component.Loader
import com.hoppers.max.presenter.ui.component.Page
import com.hoppers.max.presenter.ui.navigations.Graph
import com.hoppers.max.presenter.viewmodels.JobViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JobsScreenTab() {
    Page(pageTitle = "Jobs") {
        val jobViewModel = hiltViewModel<JobViewModel>()
        val navController = LocalNavController.current
        val jobList = remember { mutableStateListOf<Job>() }
        //Job List
        when (val result = jobViewModel.state.collectAsState().value) {
            is UiState.Content -> {
                (result.data as? JobResponse)?.let {
                    LaunchedEffect(key1 = Unit) {
                        jobList.addAll(it.jobs)
                    }
                }
            }
            is UiState.Error -> Error(result.message)
            is UiState.Loading -> Loader()
            is UiState.Empty -> {}
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            LazyColumn(
                    verticalArrangement = Arrangement.spacedBy((1).dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxHeight(),
                ) {

                    itemsIndexed(
                        items = jobList,
                        key = { index, data -> data.id.toString() + index }
                    ) { _, item ->

                        val modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .combinedClickable {
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set(KEY_ITEM_SELECTED, item)
                                navController.navigate(Graph.DETAILS)
                            }
                        ListItem(item, modifier = modifier)
                    }
                    //  itemContent = { ListItem(item, isEditActivated) }
                }
        }
    }
}

@Composable
private fun ListItem(job: Job, modifier: Modifier) {
    val contentColor = LocalContentColor.current
    Card(
        modifier = modifier.padding(8.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(contentColor = contentColor, containerColor = Color.White)
    ) {
        Column {

            Text(
                text = job.positionTitle.orEmpty(),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )
            Text(
                text = "${job.location}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = contentColor.copy(alpha = 0.5f),
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = job.description.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
            )

        }

    }
}

class CalDataPreviewParameterProvider : PreviewParameterProvider<Job> {

    override val values = sequenceOf(
        Job(
            "Elise",
            positionTitle = "Address",
            location = 0,
            haveIApplied = true,
            industry = 90,
            description = "Description",
            salaryRange = SalaryRange(0, 900)

        ),

        )
}

@Preview(showSystemUi = false)
@Composable
fun ComposablePreview(@PreviewParameter(CalDataPreviewParameterProvider::class) job: Job) {
    JobsScreenTab()
}