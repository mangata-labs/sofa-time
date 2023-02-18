package com.mangata.tvshow_presentation.tvShowHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mangata.core_ui.theme.textPrimaryDim

@Composable
fun WorkInProgressCard(
    onInfoClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .offset(x = -(20.dp), y = 15.dp)
                .size(size = 18.dp)
                .clip(shape = RoundedCornerShape(percent = 50))
                .background(color = MaterialTheme.colors.secondary)
                .padding(2.dp)
                .wrapContentSize(Alignment.Center)
                .align(Alignment.TopEnd)
                .clickable { onInfoClick() }
        ) {
            Icon(
                tint = Color.DarkGray,
                imageVector = Icons.Filled.PriorityHigh,
                contentDescription = "Warning"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp, 60.dp),
                    tint = MaterialTheme.colors.textPrimaryDim.copy(alpha = 0.18f),
                    imageVector = Icons.Outlined.Image,
                    contentDescription = "Placeholder Image"
                )
            }
            Column(
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Text(
                    text = "The Last of Us",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.textPrimaryDim.copy(alpha = 0.25f)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Season 1 - Episode 4",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.textPrimaryDim.copy(alpha = 0.25f)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(Color.Red.copy(alpha = 0.18f))
                        .clip(RoundedCornerShape(percent = 30))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(3.dp)
                            .background(Color.Red.copy(alpha = 0.18f))
                            .clip(RoundedCornerShape(percent = 30))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WorkInProgressCardPreview() {
    WorkInProgressCard(onInfoClick = {})
}