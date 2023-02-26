package com.mangata.tvshow_presentation.tvShowDetail.components.storySection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim

@Composable
internal fun StorySection(
    modifier: Modifier = Modifier,
    story: String,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "The Story",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.textPrimary
            )
            Text(
                text = story,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.textPrimaryDim,
            )
        }
    }
}