package app.ui.screen.home.params

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.screen.home.HomeComponent
import app.ui.screen.home.HomeState
import app.ui.theme.Resources

@Composable
fun DealParams(
    modifier: Modifier = Modifier,
    component: HomeComponent,
    state: HomeState,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = Resources.dimens.screenSpacingSmall),
    ) {
        item {
            SortingParam(type = state.sortingType, onChangeType = { component.changeSorting(it) })
        }

    }
}
