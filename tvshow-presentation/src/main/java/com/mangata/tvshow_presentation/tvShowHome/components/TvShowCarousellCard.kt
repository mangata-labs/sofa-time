package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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
import androidx.compose.ui.graphics.Brush
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTvShowClick(tvShow.id) },
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp
    ) {
        Box(
            modifier = Modifier.height(220.dp)
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.75f),
                        text = tvShow.name,
                        style = MaterialTheme.typography.h4,
                        color = Color.White
                    )
                    TextWithIcon(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(SemiDarkGray)
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        color = Color.White,
                        text = tvShow.voteAverage.round(1),
                        icon = Icons.Filled.Star,
                        iconColor = MaterialTheme.colors.secondary,
                        size = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}