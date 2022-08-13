package com.mangata.tvshow_presentation.tvSearch.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core_ui.theme.componentBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_presentation.R

@Composable
fun TvShowCard(
    tvShow: TvShow,
    onTvDetailClick: (Int) -> Unit,
    imageLoader: ImageLoader
) {
    Card(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .clickable { onTvDetailClick(tvShow.id) },
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 10.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.textPrimary,
                style = MaterialTheme.typography.body1,
                text = tvShow.name,
            )
        }
    }
}