package com.example.rickandmortyxml.core.base

sealed class UiState {
    object PendingState : UiState()
    object IdleState : UiState()
}
