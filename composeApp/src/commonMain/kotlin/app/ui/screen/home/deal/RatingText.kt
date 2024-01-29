package app.ui.screen.home.deal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.screen.home.rating.MetacriticRating
import app.ui.screen.home.rating.SteamRating
import app.ui.theme.Resources
import domain.deal.DealModel

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
            SteamRating(
                modifier = modifier,
                ratingText = ratingText,
                style = style,
                steamRatingPercent = deal.steamRatingPercent,
                steamRatingCount = deal.steamRatingCount,
            )
        }
        val metaScore = deal.metacriticScore.toIntOrNull()
        if (metaScore != null && metaScore > 0) {
            Text(text = " | ", style = style)
            MetacriticRating(modifier = modifier, style = style, metaScore = metaScore)
        }
    }
}
