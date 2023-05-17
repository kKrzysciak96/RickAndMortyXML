package com.example.rickandmortyxml.core.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyxml.core.exception.ErrorMapper
import com.hadilq.liveevent.LiveEvent

open class BaseViewModel(private val errorMapper: ErrorMapper) : ViewModel(),
    DefaultLifecycleObserver {
    private val _message by lazy { LiveEvent<String>() }
    val message: LiveData<String>
        get() = _message

    private val _uiState by lazy { MutableLiveData<UiState>(UiState.IdleState) }
    val uiState: LiveData<UiState>
        get() = _uiState


    protected fun setPendingState() {
        _uiState.value = UiState.PendingState
    }

    protected fun setIdleState() {
        _uiState.value = UiState.IdleState
    }

    private fun setMessage(message: String) {
        _message.value = message
    }

    protected fun handleFailure(throwable: Throwable) {
        val errorMessage = errorMapper.map(throwable)
        setMessage(errorMessage)
    }


}