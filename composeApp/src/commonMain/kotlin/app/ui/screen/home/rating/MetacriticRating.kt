package app.ui.screen.home.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import app.ui.theme.Resources
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

private const val MIN_POSITIVE_PERCENT = 70
private const val MIN_MEDIUM_PERCENT = 40

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MetacriticRating(
    modifier: Modifier,
    style: TextStyle,
    metaScore: Int,
) {
    Image(
        modifier = Modifier.size(Resources.dimens.dealIconSize),
        painter = painterResource("metacritic_icon.xml"),
        contentDescription = null,
    )
    Text(
        modifier = modifier,
        text = " $metaScore",
        style = style,
        color = when {
            metaScore > MIN_POSITIVE_PERCENT -> MaterialTheme.colorScheme.tertiary
            metaScore > MIN_MEDIUM_PERCENT -> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.error
        }
    )
}

