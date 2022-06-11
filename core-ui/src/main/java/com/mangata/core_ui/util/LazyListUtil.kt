package com.mangata.core_ui.util

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.loadMore(buffer: Int = 2) :Boolean {
    val layoutInfo = this.layoutInfo
    val totalItemsNumber = layoutInfo.totalItemsCount
    val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
    return lastVisibleItemIndex > (totalItemsNumber - buffer)
}