package com.mangata.tvshow_presentation.tvShowDetail.components.networkSection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core_ui.theme.SnowWhite
import com.mangata.core_ui.theme.cardBackground
import com.mangata.tvshow_domain.model.tvShowDetail.Network

@Composable
internal fun NetworkSection(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    networks: List<Network>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(networks) { network ->
            NetworkChip(imageLoader, url = network.logoPath)
        }
        item {
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}

@Composable
private fun NetworkChip(imageLoader: ImageLoader, url: String?) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colors.cardBackground),
                shape = MaterialTheme.shapes.medium
            )
            .background(color = SnowWhite, shape = MaterialTheme.shapes.medium)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 90.dp, height = 45.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}