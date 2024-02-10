package app.ui.dialog.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import app.ui.composable.image.AsyncImage
import app.ui.screen.home.params.shop.ShopDisplayable
import app.ui.theme.Resources

@Composable
fun ShopDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: (List<ShopDisplayable>) -> Unit,
    shops: List<ShopDisplayable>,
) {
    var dialogShops by remember { mutableStateOf(shops) }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = Resources.strings.shops(dialogShops.count { it.checked }),
                textAlign = TextAlign.Center,
            )
        },
        text = {
            val height = Resources.dimens.shopItemHeight
            val shape = MaterialTheme.shapes.small
            val padding = Resources.dimens.viewsSpacingExtraSmall
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                item(
                    span = { GridItemSpan(maxCurrentLineSpan) }
                ) {
                    AllShopsCheck(
                        height = height,
                        shape = shape,
                        padding = padding,
                        shops = dialogShops,
                        updateShops = { dialogShops = it }
                    )
                }
                itemsIndexed(
                    items = dialogShops,
                    key = { index, item -> item.storeID },
                ) { index, shop ->
                    Row(
                        modifier = Modifier
                            .height(height)
                            .clip(shape)
                            .clickable {
                                dialogShops = dialogShops.toMutableList().apply {
                                    val item = this[index]
                                    this[index] = item.copy(checked = !item.checked)
                                }
                            }
                            .padding(padding),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = shop.checked,
                            onCheckedChange = null,
                        )
                        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                        AsyncImage(
                            modifier = Modifier.size(Resources.dimens.shopIconSizeSmall),
                            url = shop.images.logo,
                        )
                        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                        Text(text = shop.storeName, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        },
        confirmButton = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier.align(Alignment.Center).fillMaxWidth(0.7f),
                    onClick = { onConfirm(dialogShops) },
                    enabled = dialogShops.any { it.checked },
                ) {
                    Text(text = Resources.strings.confirm)
                }
            }
        }
    )
}
