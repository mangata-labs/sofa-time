package com.mangata.core_ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebViewScreen(
    modifier: Modifier = Modifier,
    webUrl: String
) {
    val state = rememberWebViewState(url = webUrl)
    WebView(
        modifier = modifier.fillMaxSize(),
        state = state,
        onCreated = { it.settings.javaScriptEnabled = true }
    )
}