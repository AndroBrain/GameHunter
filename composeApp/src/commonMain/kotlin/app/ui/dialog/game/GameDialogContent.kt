package app.ui.dialog.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import app.ui.composable.image.AsyncImage
import app.ui.dialog.game.model.GameWithDealsDisplayable
import app.ui.theme.Resources

@Composable
fun GameDialogContent(
    modifier: Modifier = Modifier,
    gameWithDeals: GameWithDealsDisplayable,
    onDealClick: (String) -> Unit,
    setupAlert: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            AsyncImage(
                modifier = Modifier.size(
                    width = Resources.dimens.dealImageWidth,
                    height = Resources.dimens.dealImageHeight,
                ).clip(CardDefaults.shape),
                url = gameWithDeals.game.thumb,
            )
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Column(modifier = Modifier.height(Resources.dimens.dealImageHeight)) {
                Text(
                    text = gameWithDeals.game.title,
                    minLines = 2,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${gameWithDeals.game.cheapestPriceEver}${Resources.strings.currencySign}",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = Resources.strings.cheapestPriceEver,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingSmall))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = setupAlert) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Text(
                text = Resources.strings.getInTheseShops,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingSmall))
        val shopItemModifier = Modifier.fillMaxWidth()
        LazyColumn {
            items(gameWithDeals.deals) { deal ->
                Divider()
                GameDeal(
                    modifier = shopItemModifier,
                    onClick = { onDealClick(deal.dealID) },
                    deal = deal
                )
                Divider()
            }
        }
    }
}