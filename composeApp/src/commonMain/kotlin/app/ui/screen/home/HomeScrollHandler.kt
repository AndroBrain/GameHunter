package app.ui.screen.home

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalFocusManager

private const val ITEMS_TO_SHOW_MORE_DEALS = 5

@Composable
fun HomeScrollHandler(gridState: LazyGridState, getDeals: () -> Unit) {
    val showMore by remember {
        derivedStateOf {
            val visibleItemsInfo = gridState.layoutInfo.visibleItemsInfo
            val lastVisibleItem = visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index + ITEMS_TO_SHOW_MORE_DEALS > gridState.layoutInfo.totalItemsCount
        }
    }
    LaunchedEffect(showMore) {
        if (showMore) {
            getDeals()
        }
    }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(gridState.firstVisibleItemIndex) {
        focusManager.clearFocus()
    }
}
