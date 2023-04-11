package com.hoppers.max.presenter.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoppers.max.constants.KEY_TOKEN
import com.hoppers.max.data.StorageHandler
import com.hoppers.max.data.UiState
import com.hoppers.max.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository,
    private val storageHandler: StorageHandler,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val state = _uiState.asStateFlow()

    fun doLogin(username: String, password: String) {
        viewModelScope.launch {
            flow {
                emit(UiState.Loading)
                //TODO()   Uncomment this line to implement  real API Call
                kotlinx.coroutines.delay(1000)
                // Uncomment when api ready
                val authToken = "repository.doLogin(username, password)"
                if (authToken.isBlank()) {
                    emit(UiState.Error("Unexpected result found, try again"))
                    return@flow
                }
                emit(UiState.Content(authToken))
                storageHandler.save(KEY_TOKEN, authToken)

            }.catch {
                Log.d(javaClass::class.java.name, "doLogin: $it.")
                emit(UiState.Error(it.message.toString()))
            }
                .flowOn(Dispatchers.IO).collect {
                    _uiState.value = it
                }
        }
    }

    fun resetState() = run { _uiState.value = UiState.Empty }
}