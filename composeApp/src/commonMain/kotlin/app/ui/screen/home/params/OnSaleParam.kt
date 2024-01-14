package app.ui.screen.home.params

import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.ui.theme.Resources

@Composable
fun OnSaleParam(
    modifier: Modifier = Modifier,
    onSale: Boolean,
    onClick: () -> Unit,
) {
    FilterChip(
        modifier = modifier,
        selected = onSale,
        onClick = onClick,
        label = {
            DealParamText(text = Resources.strings.onSaleOnly(onSale))
        },
        colors = DealParamDefaults.colors(),
    )
}
