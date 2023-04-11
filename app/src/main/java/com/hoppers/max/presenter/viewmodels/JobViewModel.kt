package com.hoppers.max.presenter.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoppers.max.data.UiState
import com.hoppers.max.domain.Job
import com.hoppers.max.domain.JobResponse
import com.hoppers.max.domain.Repository
import com.hoppers.max.domain.SalaryRange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    init {
        //ToDo  Replace limit and page as per Pagination
        findJobs(0, 10)
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val state = _uiState.asStateFlow()

    //Mocked Data
    private val values = mutableListOf(
        Job(
            "0",
            positionTitle = "Software Development Engineer II (SDE II)",
            location = 0,
            haveIApplied = true,
            industry = 70,
            description = "We are seeking a highly motivated and experienced Software Development Engineer II (SDE II) to join our team. In this role, you will be responsible for designing, developing, and maintaining complex software systems. You will work closely with cross-functional teams to develop scalable solutions and contribute to the technical vision of the product. You will be a key player in ensuring the reliability and stability of the systems, and you will have a direct impact on the success of the product.",
            salaryRange = SalaryRange(0, 600)

        ),
        Job(
            "1",
            positionTitle = "Kotlin Developer",
            location = 1,
            haveIApplied = true,
            industry = 80,
            description = "We are seeking a highly skilled Kotlin Developer to join our team. The successful candidate will be responsible for developing and maintaining cutting-edge software solutions using Kotlin programming language. The ideal candidate must have a strong understanding of object-oriented programming concepts, solid experience in developing applications using Kotlin, and be able to work in a fast-paced environment.",
            salaryRange = SalaryRange(0, 900)

        ),
        Job(
            "2",
            positionTitle = "Python Developer",
            location = 2,
            haveIApplied = false,
            industry = 90,
            description = "We are seeking an experienced Python Developer to join our dynamic team. The ideal candidate will be responsible for designing, developing, and implementing Python-based software solutions. You will collaborate with cross-functional teams to define, design, and deliver new features and functionality for our products.",
            salaryRange = SalaryRange(0, 700)

        ),
        Job(
            "2",
            positionTitle = "Job title 4",
            location = 2,
            haveIApplied = false,
            industry = 90,
            description = "Description",
            salaryRange = SalaryRange(0, 700)

        ),
        Job(
            "2",
            positionTitle = "Job title 5",
            location = 2,
            haveIApplied = false,
            industry = 90,
            description = "Description",
            salaryRange = SalaryRange(0, 700)

        ),
        Job(
            "2",
            positionTitle = "Job title 6",
            location = 2,
            haveIApplied = false,
            industry = 90,
            description = "Description",
            salaryRange = SalaryRange(0, 700)

        ),

        )

    private fun findJobs(limit: Int, page: Int) {
        viewModelScope.launch {
            flow {
                emit(UiState.Loading)
                kotlinx.coroutines.delay(2000)
                val jobResponse =
                    JobResponse(jobs = values, hasNext = false, size = 0, total = 10, page = 1)
                //TODO()   Uncomment this line to implement  real API Call
                /*repository.findJobs(limit, page)*/

                if (jobResponse.jobs.isEmpty())
                    emit(UiState.Empty)
                else {
                    emit(UiState.Content(jobResponse))
                }
            }.catch {
                Log.d(javaClass::class.java.name, "finJobs: $it")
                emit(UiState.Error(it.message.toString()))
            }
                .flowOn(Dispatchers.IO).collect {
                    _uiState.value = it
                }
        }
    }


}