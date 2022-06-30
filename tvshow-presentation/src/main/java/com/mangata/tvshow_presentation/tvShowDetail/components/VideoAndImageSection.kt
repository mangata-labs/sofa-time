package com.mangata.tvshow_presentation.tvShowDetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import com.mangata.tvshow_presentation.R

@Composable
fun VideoAndImageSection(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    videoModel: Video?,
    posterModel: List<Poster>,
    onPlayVideoClick: (String) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        videoModel?.let { video ->
            item {
                VideoItem(video, onPlayVideoClick)
            }
        }
        itemsIndexed(posterModel) { index, poster ->
            ImageItem(
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
    Box(modifier = Modifier
        .width(170.dp)
        .height(100.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(Color.LightGray)
        .wrapContentSize(Alignment.Center)
        .clickable {
            video.createUrl()?.let { onPlayVideoClick(it) }
        }
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            tint = Color.White,
            imageVector = Icons.Outlined.PlayCircle,
            contentDescription ="Play Video")
    }
}

@Composable
private fun ImageItem(
    imageLoader: ImageLoader,
    poster: Poster
) {
    AsyncImage(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(5.dp)),
        model = ImageRequest.Builder(LocalContext.current)
            .data(poster.filePath ?: R.drawable.image_placeholder)
            .placeholder(R.drawable.image_placeholder)
            .crossfade(true)
            .build(),
        imageLoader = imageLoader,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}