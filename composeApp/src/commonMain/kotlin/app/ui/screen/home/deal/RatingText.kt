package app.ui.screen.home.deal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import app.ui.theme.Resources
import domain.deal.DealModel

private const val MIN_POSITIVE_PERCENT = 70
private const val MIN_MEDIUM_PERCENT = 40

private const val MIN_POSITIVE_RATING = 7
private const val MIN_MEDIUM_RATING = 4

@Composable
fun RatingText(modifier: Modifier = Modifier, deal: DealModel) {
    val ratingText =
        deal.steamRatingText ?: (deal.steamRatingPercent.toString() + "%")
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingExtraSmall))
        val style = MaterialTheme.typography.bodyMedium
        Text(
            text = Resources.strings.dealRating,
            style = style,
        )
        Text(
            text = deal.dealRating,
            style = MaterialTheme.typography.titleMedium,
            color = when {
                deal.dealRating.toFloat() > MIN_POSITIVE_RATING -> MaterialTheme.colorScheme.tertiary
                deal.dealRating.toFloat() > MIN_MEDIUM_RATING -> MaterialTheme.colorScheme.secondary
                else -> MaterialTheme.colorScheme.error
            }
        )
        if (deal.steamRatingCount > 0) {
            Text(
                text = Resources.strings.steamRating,
                style = style,
            )
            Text(
                modifier = modifier,
                text = ratingText,
                style = style,
                color = when {
                    deal.steamRatingPercent > MIN_POSITIVE_PERCENT -> MaterialTheme.colorScheme.tertiary
                    deal.steamRatingPercent > MIN_MEDIUM_PERCENT -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.error
                }
            )
            Text(
                text = ", ${deal.steamRatingCount}",
                style = style,
                minLines = 1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
