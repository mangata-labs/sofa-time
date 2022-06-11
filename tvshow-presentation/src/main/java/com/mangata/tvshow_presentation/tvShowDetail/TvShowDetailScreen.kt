package com.mangata.tvshow_presentation.tvShowDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TvShowDetailScreen(
    tvShowID: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "TV SHOW Detail Screen for $tvShowID")
    }
}