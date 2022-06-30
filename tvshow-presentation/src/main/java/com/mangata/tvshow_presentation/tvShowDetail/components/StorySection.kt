package com.mangata.tvshow_presentation.tvShowDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StorySection(
    modifier: Modifier = Modifier,
    story: String,
) {
    Box(modifier = modifier
        .fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                text = "The Story")
            Text(
                fontSize = 13.sp,
                text = story)
        }
    }
}