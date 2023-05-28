package com.mangata.tvshow_presentation.tvShowDetail.components.mediaSection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.tvshow_domain.model.image.Poster
import com.mangata.tvshow_domain.model.video.Video
import com.mangata.core_ui.R as CoreUI

@Composable
internal fun MediaSection(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    video: Video?,
    posters: List<Poster>,
    onPlayVideoClick: (String) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
    ) {
        video?.let { video ->
            item {
                VideoItem(video, onPlayVideoClick)
            }
        }
        itemsIndexed(posters) { _, poster ->
            PosterItem(
                imageLoader = imageLoader,
                poster = poster,
            )
        }
    }
}

@Composable
private fun VideoItem(
    video: Video,
    onPlayVideoClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(115.dp)
            .clip(MaterialTheme.shapes.small)
            .background(Color.LightGray)
            .clickable {
                video
                    .createUrl()
                    ?.let { onPlayVideoClick(it) }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            tint = Color.White,
            imageVector = Icons.Outlined.PlayCircle,
            contentDescription = "Play Video"
        )
    }
}

@Composable
private fun PosterItem(
    imageLoader: ImageLoader,
    poster: Poster
) {
    AsyncImage(
        modifier = Modifier
            .size(115.dp)
            .clip(MaterialTheme.shapes.small),
        model = ImageRequest.Builder(LocalContext.current)
            .data(poster.filePath)
            .placeholder(CoreUI.drawable.image_placeholder)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}