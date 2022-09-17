package com.mangata.core_ui.util


sealed class UiEvent {
    data class SnackbarEvent(val uiText: String, val uiActionLabel: String? = null) : UiEvent()
}