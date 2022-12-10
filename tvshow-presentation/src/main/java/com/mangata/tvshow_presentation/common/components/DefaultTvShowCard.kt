package com.mangata.tvshow_presentation.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.core_ui.R as CoreUI

@Composable
fun DefaultTvShowCard(
    imageLoader: ImageLoader,
    tvShow: TvShow,
    imageHeight: Dp,
    imageWidth: Dp,
    onTvDetailClick: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Card(
            modifier = Modifier.clickable { onTvDetailClick(tvShow.id) },
            shape = MaterialTheme.shapes.medium,
            elevation = 0.dp
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(height = imageHeight, width = imageWidth),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShow.backdropPath)
                    .placeholder(CoreUI.drawable.image_placeholder)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            modifier = Modifier.width(imageWidth),
            text = tvShow.name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.textPrimary,
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}