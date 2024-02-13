package app.ui.screen.home.deal

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import app.ui.composable.image.AutoSizedByHeightAsyncImage
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
        Column(
            modifier = Modifier.fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                    onClick = onClick,
                ),
        ) {
            Row {
                AutoSizedByHeightAsyncImage(
                    url = deal.thumb,
                    targetWidth = Resources.dimens.dealImageWidth,
                    targetHeight = Resources.dimens.dealImageHeight,
                    shape = CardDefaults.shape,
                )
                Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                Column(modifier = Modifier.heightIn(min = Resources.dimens.dealImageHeight)) {
                    val currencySign = Resources.strings.currencySign
                    Text(
                        modifier = Modifier.padding(end = Resources.dimens.viewsSpacingSmall),
                        text = deal.title,
                        minLines = 2,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Spacer(modifier = Modifier.weight(1F))
                        if (deal.isOnSale) {
                            Text(
                                text = "-${deal.savings}%",
                                color = MaterialTheme.colorScheme.tertiary,
                                style = MaterialTheme.typography.headlineMedium,
                            )
                            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingExtraSmall))
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    modifier = Modifier.alpha(0.8F),
                                    text = "${deal.normalPrice}$currencySign",
                                    style = MaterialTheme.typography.titleSmall.copy(textDecoration = TextDecoration.LineThrough),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                                Text(
                                    text = "${deal.salePrice}$currencySign",
                                    color = MaterialTheme.colorScheme.tertiary,
                                    style = MaterialTheme.typography.titleSmall,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            }
                        } else {
                            Text(
                                text = "${deal.salePrice}$currencySign",
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                    }
                }
            }
            RatingText(
                modifier = Modifier.padding(Resources.dimens.viewsSpacingExtraSmall),
                deal = deal,
            )
            Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingExtraSmall))
        }
    }
}
