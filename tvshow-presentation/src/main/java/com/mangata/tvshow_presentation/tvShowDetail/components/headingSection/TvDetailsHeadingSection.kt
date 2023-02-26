package com.mangata.tvshow_presentation.tvShowDetail.components.headingSection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangata.core.extensions.round
import com.mangata.core_ui.components.TextWithIcon
import com.mangata.core_ui.theme.cardBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim
import com.mangata.tvshow_presentation.tvShowDetail.events.TvShowDetailEvent
import com.mangata.tvshow_presentation.tvShowDetail.viewModel.TvShowDetailViewModel
import com.mangata.core_ui.R as CoreUI

@Composable
internal fun TvDetailsHeadingSection(
    modifier: Modifier = Modifier,
    viewModel: TvShowDetailViewModel
) {
    val model = viewModel.headerState
    val isAdded = viewModel.isAddedToWatchList

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        HeaderRow(headerModel = model) {
            Text(
                modifier = Modifier.fillMaxWidth(0.80f),
                color = MaterialTheme.colors.textPrimary,
                style = MaterialTheme.typography.h1,
                text = model.title
            )
            IconButton(
                modifier = Modifier.offset(x = ((-3).dp)),
                onClick = {
                    if (isAdded) viewModel.onEvent(TvShowDetailEvent.RemoveFromWatchlist)
                    else viewModel.onEvent(TvShowDetailEvent.AddedToWatchList)
                }
            ) {
                Icon(
                    tint = if (isAdded) MaterialTheme.colors.primary
                    else MaterialTheme.colors.textPrimary,
                    painter = painterResource(
                        id =
                        if (isAdded) CoreUI.drawable.ic_circle_filled_add
                        else CoreUI.drawable.ic_circle_outlined_add
                    ),
                    modifier = Modifier.size(40.dp),
                    contentDescription = "Add to WatchList"
                )
            }
        }
        HeaderRow(headerModel = model) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.textPrimary
                        )
                    ) { append(it.displayDate()) }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.textPrimaryDim
                        )
                    ) { append("  ●  ${it.displaySeasons()}") }
                },
                style = MaterialTheme.typography.body1
            )
            RatingItem(score = it.score.round(1))
        }
        if (model.genres.isNotEmpty()) {
            HeaderRow(headerModel = model) {
                Text(
                    color = MaterialTheme.colors.textPrimary,
                    style = MaterialTheme.typography.body1,
                    text = it.displayGenres()
                )
            }
        }
        HeaderRow(headerModel = model) {
            val inlineContent = mapOf(
                Pair("inlineIcon",
                    InlineTextContent(
                        Placeholder(
                            width = 16.sp,
                            height = 16.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                        )
                    ) {
                        Icon(
                            Icons.Outlined.Timer,
                            "Stopwatch",
                            tint = MaterialTheme.colors.textPrimaryDim
                        )
                    }
                )
            )
            Text(
                color = MaterialTheme.colors.textPrimaryDim,
                style = MaterialTheme.typography.body1,
                text = buildAnnotatedString {
                    if (it.runTime != null) {
                        append("${it.runTime} min ")
                    } else {
                        append(" - ")
                    }
                    appendInlineContent("inlineIcon", "[icon]")
                },
                inlineContent = inlineContent
            )
        }
    }
}

@Composable
private fun HeaderRow(
    modifier: Modifier = Modifier,
    headerModel: TvDetailsHeadingModel,
    rowElement: @Composable (TvDetailsHeadingModel) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        rowElement(headerModel)
    }
}

@Composable
private fun RatingItem(
    score: String,
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.cardBackground)
            .padding(vertical = 5.dp, horizontal = 10.dp),
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