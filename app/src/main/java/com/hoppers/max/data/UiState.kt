package com.hoppers.max.data

sealed class UiState {
    // Success
    data class Content(val data: Any) : UiState()

    // Error
    data class Error(val message: String) : UiState()

    // Background Processing
    object Loading : UiState()

    // Initial
    object Empty : UiState()
}
