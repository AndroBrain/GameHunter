package app.ui.dialog.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import app.ui.screen.home.params.shop.ShopDisplayable
import app.ui.theme.Resources

@Composable
fun AllShopsCheck(
    height: Dp,
    shape: CornerBasedShape,
    padding: Dp,
    shops: List<ShopDisplayable>,
    updateShops: (List<ShopDisplayable>) -> Unit,
) {
    Row(
        modifier = Modifier
            .height(height)
            .clip(shape)
            .clickable {
                fun checkAll(checked: Boolean) {
                    updateShops(
                        shops.toMutableList().apply {
                            forEachIndexed { index, shopDisplayable ->
                                this[index] = shopDisplayable.copy(checked = checked)
                            }
                        }
                    )
                }
                if (shops.all { it.checked }) {
                    checkAll(false)
                } else {
                    checkAll(true)
                }
            }
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = shops.all { it.checked },
            onCheckedChange = null,
        )
        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
        Text(
            text = Resources.strings.allShops,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
