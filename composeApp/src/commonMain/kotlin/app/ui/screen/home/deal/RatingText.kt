package app.ui.screen.home.deal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import app.ui.theme.Resources
import domain.deal.DealModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

private const val MIN_POSITIVE_PERCENT = 70
private const val MIN_MEDIUM_PERCENT = 40

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RatingText(modifier: Modifier = Modifier, deal: DealModel) {
    val ratingText =
        deal.steamRatingText ?: (deal.steamRatingPercent.toString() + "%")
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingExtraSmall))
        DealRating(deal = deal)
        val style = MaterialTheme.typography.bodyMedium
        if (deal.steamRatingCount > 0) {
            Text(text = " | ", style = style)
            Image(
                modifier = Modifier.size(Resources.dimens.dealIconSize),
                painter = painterResource("steam_icon.xml"),
                contentDescription = null,
            )
            Text(
                modifier = modifier,
                text = " $ratingText",
                style = style,
                color = when {
                    deal.steamRatingPercent > MIN_POSITIVE_PERCENT -> MaterialTheme.colorScheme.tertiary
                    deal.steamRatingPercent > MIN_MEDIUM_PERCENT -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.error
                }
            )
            Text(
                text = " (${
                    if (deal.steamRatingCount > 1000) {
                        "${deal.steamRatingCount / 1000}k"
                    } else {
                        deal.steamRatingCount
                    }
                })",
                style = style,
                minLines = 1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        val metaScore = deal.metacriticScore.toIntOrNull()
        if (metaScore != null && metaScore > 0) {
            Text(text = " | ", style = style)
            Image(
                modifier = Modifier.size(Resources.dimens.dealIconSize),
                painter = painterResource("metacritic_icon.xml"),
                contentDescription = null,
            )
            Text(
                modifier = modifier,
                text = " ${deal.metacriticScore}",
                style = style,
                color = when {
                    metaScore > MIN_POSITIVE_PERCENT -> MaterialTheme.colorScheme.tertiary
                    metaScore > MIN_MEDIUM_PERCENT -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.error
                }
            )
        }
    }
}
