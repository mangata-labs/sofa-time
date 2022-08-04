package com.mangata.tvshow_presentation.tvShowDetail.components.headerSection

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangata.core.extensions.round
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    headerModel: TvDetailsHeaderModel?,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        headerModel?.let {
            HeaderRow(headerModel = it) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.80f),
                    color = MaterialTheme.colors.textPrimary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = it.title
                )
                IconButton(
                    modifier = Modifier.offset(x = (5.dp)),
                    onClick = { println("Not Implemented") }) {
                    Icon(
                        tint = MaterialTheme.colors.textPrimary,
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = "Add to WatchList"
                    )
                }
            }
            HeaderRow(headerModel = it) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = MaterialTheme.colors.textPrimary)
                        ) { append(it.displayDate()) }
                        withStyle(style = SpanStyle(
                            color = MaterialTheme.colors.textPrimaryDim)
                        ) { append("  ●  ${it.numberOfSeasons} Seasons") }
                    },
                    fontSize = 16.sp
                )
                RatingItem(score = it.score.round(1))
            }
            HeaderRow(headerModel = it) {
                Text(
                    color = MaterialTheme.colors.textPrimary,
                    fontSize = 16.sp,
                    text = it.displayGenres()
                )
            }
            HeaderRow(headerModel = it) {
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
                    fontSize = 16.sp,
                    text = buildAnnotatedString {
                        append("${it.runTime} min")
                        append(" ")
                        appendInlineContent("inlineIcon", "[icon]")
                    },
                    inlineContent = inlineContent
                )
            }
        }
    }
}

@Composable
private fun HeaderRow(
    modifier: Modifier = Modifier,
    headerModel: TvDetailsHeaderModel,
    rowElement: @Composable (TvDetailsHeaderModel) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        rowElement(headerModel)
    }
}
