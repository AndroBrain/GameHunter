package app.ui.screen.home.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import app.ui.theme.Resources
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

private const val MIN_POSITIVE_PERCENT = 70
private const val MIN_MEDIUM_PERCENT = 40

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SteamRating(
    modifier: Modifier,
    ratingText: String,
    style: TextStyle,
    steamRatingPercent: Int,
    steamRatingCount: Int,
) {
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
            steamRatingPercent > MIN_POSITIVE_PERCENT -> MaterialTheme.colorScheme.tertiary
            steamRatingPercent > MIN_MEDIUM_PERCENT -> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.error
        }
    )
    Text(
        text = " (${
            if (steamRatingCount > 1000) {
                "${steamRatingCount / 1000}k"
            } else {
                steamRatingCount
            }
        })",
        style = style,
        minLines = 1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}
