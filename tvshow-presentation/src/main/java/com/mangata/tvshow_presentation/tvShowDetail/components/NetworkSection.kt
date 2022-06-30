package com.mangata.tvshow_presentation.tvShowDetail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core_ui.theme.Shapes
import com.mangata.tvshow_domain.model.tvShowDetail.Network

@Composable
fun NetworkSection(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    networks: List<Network>
) {
    LazyRow (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
            items(networks) { network ->
                NetworkChip(imageLoader ,url = network.logo_path)
            }
    }
}

@Composable
private fun NetworkChip(imageLoader: ImageLoader, url: String?) {
    Box(modifier = Modifier
        .border(
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(30)
        )
        .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        AsyncImage(
            modifier = Modifier.size(width = 50.dp, height = 25.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}