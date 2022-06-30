package com.mangata.tvshow_presentation.tvShowDetail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangata.core.extensions.round
import com.mangata.tvshow_presentation.tvShowDetail.DetailHeaderModel

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    headerModel: DetailHeaderModel?,
) {
    headerModel?.let {
        Column(modifier = modifier) {
            HeaderRow(headerModel = it) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.72f),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = it.title
                )
                IconButton(
                    modifier = Modifier.offset(x = (5.dp)),
                    onClick = { println("Not Implemented") }) {
                    Icon(
                        tint = Color.Black,
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = "Add to WatchList"
                    )
                }
            }

            HeaderRow(
                modifier = modifier.padding(top = 10.dp),
                headerModel = it) {
                Text(
                    fontSize = 13.sp,
                    text = "${it.displayDate()}  ●  ${it.numberOfSeasons} Seasons"
                )
                RatingItem(score = it.score.round(1))
            }

            HeaderRow(headerModel = it) {
                Text(
                    fontSize = 13.sp,
                    text = it.displayGenres()
                )
            }

            HeaderRow(headerModel = it) {
                val inlineContent = mapOf(
                    Pair("inlineIcon",
                        InlineTextContent(
                            Placeholder(
                                width = 15.sp,
                                height = 15.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                            )
                        ) {
                            Icon(Icons.Outlined.Timer,"Stopwatch", tint = Color.DarkGray)
                        }
                    )
                )
                Text(
                    fontSize = 12.sp,
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
    headerModel: DetailHeaderModel,
    rowElement: @Composable (DetailHeaderModel) -> Unit
) {
    Spacer(modifier = Modifier.height(8.dp))
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
    score: Double
) {
    Box(modifier = Modifier
        .border(
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(30)
        )
        .padding(vertical = 6.dp, horizontal = 12.dp)
        .wrapContentSize(Alignment.Center)
    ) {
        Text(
            fontSize = 12.sp,
            text = "$score \u2B50"
        )
    }
}

