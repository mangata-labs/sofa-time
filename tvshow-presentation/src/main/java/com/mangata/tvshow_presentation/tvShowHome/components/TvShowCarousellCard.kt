package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mangata.core.extensions.round
import com.mangata.core_ui.components.TextWithIcon
import com.mangata.core_ui.theme.*
import com.mangata.tvshow_domain.model.tvShowList.TvShow
import com.mangata.tvshow_presentation.R

@Composable
fun TvShowCarouselCard(
    tvShow: TvShow,
    imageLoader: ImageLoader,
    onTvShowClick: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = MaterialTheme.shapes.medium)
            .clickable { onTvShowClick(tvShow.id) }
            .background(MaterialTheme.colors.surface)
            .padding(all = 12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tvShow.backdropPath)
                    .placeholder(R.drawable.image_placeholder)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
            )
            CardDescription(tvShow = tvShow, modifier = Modifier.fillMaxWidth())
        }
    }
}


@Composable
private fun CardDescription(
    modifier: Modifier = Modifier,
    tvShow: TvShow
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .wrapContentSize(Alignment.Center),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Top),
            text = tvShow.name,
            color = MaterialTheme.colors.textPrimary,
            style = MaterialTheme.typography.h3
        )
        RatingItem(
            modifier = Modifier.align(Alignment.Bottom),
            score = tvShow.voteAverage.round(1)
        )
    }
}

@Composable
private fun RatingItem(
    modifier: Modifier = Modifier,
    score: String,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.componentBackground)
            .padding(horizontal = 5.dp, vertical = 1.dp),
        contentAlignment = Alignment.Center
    ) {
        TextWithIcon(
            color = MaterialTheme.colors.textPrimary,
            text = score,
            icon = Icons.Filled.Star,
            iconColor = MaterialTheme.colors.secondary,
            size = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}