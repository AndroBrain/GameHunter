package app.ui.screen.home.params

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.screen.home.HomeComponent
import app.ui.screen.home.HomeState
import app.ui.screen.home.params.shop.ShopParam
import app.ui.theme.Resources
import app.ui.theme.icons.Filtericon
import app.ui.theme.icons.SortIcon

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
            Icon(imageVector = SortIcon, contentDescription = null)
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            SortingParam(type = state.sortingType, onChangeType = component::changeSorting)
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Box(
                modifier = Modifier.height(Resources.dimens.searchDividerHeight)
                    .width(DividerDefaults.Thickness)
                    .background(color = MaterialTheme.colorScheme.onSurface)
            )
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Icon(imageVector = Filtericon, contentDescription = null)
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            MaxPriceParam(maxPrice = state.maxPrice, onMaxPriceChanged = component::changeMaxPrice)
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            OnSaleParam(onSale = state.onSale, onClick = component::changeOnSale)
            if (state.shops.isNotEmpty()) {
                Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                ShopParam(shops = state.shops, onShopsChanged = component::changeShops)
            }
        }
    }
}
