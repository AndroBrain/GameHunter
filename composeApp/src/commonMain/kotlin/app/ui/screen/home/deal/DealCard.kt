package app.ui.screen.home.deal

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import app.ui.composable.image.AsyncImage
import app.ui.composable.modifier.scaleClickable
import app.ui.composable.modifier.scaleOnClick
import app.ui.theme.Resources
import domain.deal.DealModel

@Composable
fun DealCard(
    modifier: Modifier = Modifier,
    deal: DealModel,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(modifier = modifier.scaleOnClick(interactionSource)) {
        Row(
            modifier = Modifier.fillMaxSize()
                .scaleClickable(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                    onClick = onClick,
                ),
        ) {
            AsyncImage(
                modifier = Modifier.size(
                    width = Resources.dimens.dealImageWidth,
                    height = Resources.dimens.dealImageHeight,
                ).clip(CardDefaults.shape),
                url = deal.thumb,
            )
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Column(modifier = Modifier.heightIn(min = Resources.dimens.dealImageHeight)) {
                val currencySign = Resources.strings.currencySign
                Text(
                    text = deal.title,
                    minLines = 2,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1F))
                Row(verticalAlignment = Alignment.Bottom) {
                    deal.releaseDate?.let { date ->
                        Text(
                            text = date,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.weight(1F))
                    if (deal.isOnSale) {
                        Text(
                            text = "${deal.salePrice}$currencySign",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingExtraSmall))
                        Text(
                            modifier = modifier.align(Alignment.Top),
                            text = "${deal.normalPrice}$currencySign",
                            style = MaterialTheme.typography.titleSmall.copy(textDecoration = TextDecoration.LineThrough),
                            overflow = TextOverflow.Ellipsis,
                        )
                    } else {
                        Text(
                            text = "${deal.salePrice}$currencySign",
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                    Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                }
            }
        }
        if (deal.steamRatingCount > 0) {
            BottomDealText(
                modifier = Modifier.padding(Resources.dimens.viewsSpacingExtraSmall),
                deal = deal,
            )
        }
        Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingExtraSmall))
    }
}
