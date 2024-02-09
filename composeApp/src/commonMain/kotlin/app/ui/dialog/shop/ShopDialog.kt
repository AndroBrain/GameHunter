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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    onConfirm: () -> Unit,
    shops: List<ShopDisplayable>,
    // TODO add on checked change?
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = Resources.strings.shops,
                textAlign = TextAlign.Center,
            )
        },
        text = {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(shops) { shop ->
                    Row(
                        modifier = Modifier
                            .height(Resources.dimens.shopItemHeight)
                            .clip(MaterialTheme.shapes.small)
                            .clickable {
// TODO change checked state
                            }
                            .padding(Resources.dimens.viewsSpacingExtraSmall),
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
                    onClick = onConfirm,
                ) {
                    Text(text = Resources.strings.confirm)
                }
            }
        }
    )
}
