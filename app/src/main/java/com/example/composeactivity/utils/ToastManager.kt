package com.example.composeactivity.utils

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

object ToastManager {
    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent

    suspend fun showToast(message: String) {
        _toastEvent.emit(message)
    }
}