package com.mangata.core_ui.util


sealed class UICoreEvent {
    data class SnackbarEvent(val uiText: String, val uiActionLabel: String? = null) : UICoreEvent()
}