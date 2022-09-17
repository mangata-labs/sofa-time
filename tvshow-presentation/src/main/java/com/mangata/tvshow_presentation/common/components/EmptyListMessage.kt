package com.mangata.tvshow_presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_presentation.R

@Composable
fun EmptyListMessage(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.ic_sad_face),
                contentDescription = null,
                modifier = Modifier.requiredSize(70.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.textPrimary)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}