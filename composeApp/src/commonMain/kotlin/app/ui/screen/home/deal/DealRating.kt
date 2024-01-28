package app.ui.screen.home.deal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.theme.Resources
import domain.deal.DealModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

private const val MIN_POSITIVE_RATING = 7
private const val MIN_MEDIUM_RATING = 4

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DealRating(modifier: Modifier = Modifier, deal: DealModel) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val dealRatingColor = when {
            deal.dealRating.toFloat() > MIN_POSITIVE_RATING -> MaterialTheme.colorScheme.tertiary
            deal.dealRating.toFloat() > MIN_MEDIUM_RATING -> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.error
        }
        Text(
            text = "${deal.dealRating} ",
            style = MaterialTheme.typography.titleMedium,
            color = dealRatingColor
        )
        Icon(
            modifier = Modifier.size(Resources.dimens.dealIconSize),
            painter = painterResource("sell-icon.xml"),
            contentDescription = null,
            tint = dealRatingColor,
        )
    }
}
