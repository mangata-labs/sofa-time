package com.mangata.tvshow_presentation.tvShowList.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.tvshow_domain.model.TvShow
import com.mangata.tvshow_presentation.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowCard(
    tvShow: TvShow,
    onTvDetailClick: (Int) -> Unit,
    imageLoader: ImageLoader
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 8.dp),
        onClick = { onTvDetailClick(tvShow.id) }
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShow.backdropPath ?: R.drawable.image_placeholder)
                    .placeholder(R.drawable.image_placeholder)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 2.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                text = tvShow.name,
            )
        }
    }
}