package app.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import app.ui.composable.image.AsyncImage
import app.ui.theme.Resources
import domain.deal.DealModel

@Composable
fun DealCard(
    modifier: Modifier = Modifier,
    deal: DealModel,
) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier.size(Resources.dimens.dealImageSize).clip(CardDefaults.shape),
                url = deal.thumb,
            )
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingExtraSmall))
            Column {
                Text(text = deal.title, minLines = 2, maxLines = 2)
                if (deal.isOnSale) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = deal.salePrice,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingExtraSmall))
                        Text(
                            text = deal.normalPrice,
                            style = MaterialTheme.typography.titleSmall.copy(textDecoration = TextDecoration.LineThrough),
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                } else {
                    Text(
                        text = deal.salePrice,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    }
}