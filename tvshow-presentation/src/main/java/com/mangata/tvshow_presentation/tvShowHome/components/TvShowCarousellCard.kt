package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_presentation.R

@Composable
fun TvShowCarouselCard(
    tvShow: TvShow,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable { onTvShowClick(tvShow.id) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(tvShow.backdropPath)
                .placeholder(R.drawable.image_placeholder)
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}