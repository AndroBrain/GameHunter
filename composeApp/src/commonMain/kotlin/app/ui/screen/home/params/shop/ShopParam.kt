package app.ui.screen.home.params.shop

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.ui.dialog.shop.ShopDialog
import app.ui.screen.home.params.DealParamDefaults
import app.ui.screen.home.params.DealParamText
import app.ui.theme.Resources

@Composable
fun ShopParam(
    modifier: Modifier = Modifier,
    shops: List<ShopDisplayable>,
) {
    var shopVisible by remember { mutableStateOf(false) }
    if (shopVisible) {
        ShopDialog(
            onDismiss = { shopVisible = false },
            onConfirm = {
                // TODO request all the data again including this filter
                shopVisible = false
            },
            shops = shops,
        )
    }
    FilterChip(
        modifier = modifier,
        selected = false,
        onClick = { shopVisible = true },
        label = {
            Row {
                DealParamText(text = Resources.strings.shops(shops.count { it.checked }))
            }
        },
        colors = DealParamDefaults.colors(),
    )
}
