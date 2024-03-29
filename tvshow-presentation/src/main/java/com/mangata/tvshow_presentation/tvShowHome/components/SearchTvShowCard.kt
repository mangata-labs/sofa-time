package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.mangata.core_ui.theme.componentBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim

@Composable
fun SearchTvShowCard(
    modifier: Modifier = Modifier,
    onSearchCardClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface)
            .clickable { onSearchCardClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colors.primary,
                contentDescription = "Search"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "Search Tv Shows",
                    color = MaterialTheme.colors.textPrimary,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = "Search your favorite TvShows and track them in your WatchList",
                    color = MaterialTheme.colors.textPrimaryDim,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}