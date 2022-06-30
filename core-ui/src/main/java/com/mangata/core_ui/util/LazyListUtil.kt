package com.mangata.core_ui.util

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.loadMore(buffer: Int = 2) : Boolean {
    val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
    return lastVisibleItemIndex > (layoutInfo.totalItemsCount - buffer)
}