package com.mangata.tvshow_presentation.tvShowDetail.components.headerSection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangata.core_ui.components.TextWithIcon
import com.mangata.core_ui.theme.componentBackground
import com.mangata.core_ui.theme.textPrimary
import com.mangata.core_ui.theme.textPrimaryDim


@Composable
fun RatingItem(
    score: Double
) {
    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colors.componentBackground),
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 5.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        TextWithIcon(
            color = MaterialTheme.colors.textPrimary,
            text = "$score",
            icon = Icons.Filled.Star,
            size = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
