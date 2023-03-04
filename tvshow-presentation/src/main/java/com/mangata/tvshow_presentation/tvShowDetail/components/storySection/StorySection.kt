package com.mangata.tvshow_presentation.tvShowDetail.components.storySection

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim
import com.mangata.core_ui.util.noRippleClickable

@Composable
internal fun StorySection(
    modifier: Modifier = Modifier,
    story: String,
) {
    var maxLines by remember { mutableStateOf(4) }

    Box(
        modifier = modifier.fillMaxWidth().wrapContentSize(Alignment.CenterStart)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "The Story",
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.textPrimary
            )
            Box(
               modifier = Modifier.animateContentSize()
                .noRippleClickable {
                    maxLines = when(maxLines) {
                        4 -> Int.MAX_VALUE
                        else -> 4
                    }
                },
            ){
                Text(
                    text = story,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.textPrimaryDim,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}