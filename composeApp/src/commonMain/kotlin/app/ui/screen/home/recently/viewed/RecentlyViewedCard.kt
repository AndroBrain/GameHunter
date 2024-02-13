package app.ui.screen.home.recently.viewed

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import app.ui.composable.image.AutoSizedAsyncImage
import app.ui.composable.modifier.scaleOnClick
import app.ui.screen.home.rating.MetacriticRating
import app.ui.screen.home.rating.SteamRating
import app.ui.theme.Resources
import domain.game.recently.RecentlyViewedModel

@Composable
fun RecentlyViewedCard(
    modifier: Modifier = Modifier,
    model: RecentlyViewedModel,
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
                AutoSizedAsyncImage(
                    url = model.thumb,
                    targetWidth = Resources.dimens.dealImageWidth,
                    targetHeight = Resources.dimens.dealImageHeight,
                    shape = CardDefaults.shape,
                )
                Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                Text(
                    modifier = Modifier.padding(end = Resources.dimens.viewsSpacingSmall),
                    text = model.title,
                    minLines = 2,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                )
            }
            val ratingModifier = Modifier.padding(Resources.dimens.viewsSpacingExtraSmall)
            Row(
                modifier = ratingModifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
                val style = MaterialTheme.typography.bodyMedium
                if (model.steamRatingPercent != null && model.steamRatingCount != null) {
                    val ratingText =
                        model.steamRatingText ?: (model.steamRatingPercent.toString() + "%")
                    SteamRating(
                        modifier = ratingModifier,
                        ratingText = ratingText,
                        style = style,
                        steamRatingPercent = model.steamRatingPercent.toInt(),
                        steamRatingCount = model.steamRatingCount.toInt(),
                    )
                }

                val metaScore = model.metacriticScore?.toIntOrNull()

                if (metaScore != null && model.steamRatingPercent != null && model.steamRatingCount != null) {
                    Text(text = " | ", style = style)
                }

                if (metaScore != null) {
                    MetacriticRating(
                        modifier = ratingModifier,
                        style = style,
                        metaScore = metaScore
                    )
                }
                if (model.steamRatingPercent == null && (model.steamRatingCount == null || model.steamRatingCount <= 0) && metaScore == null
                ) {
                    Spacer(modifier = Modifier.height(Resources.dimens.dealIconSize))
                    Text(text = "", style = style)
                }
            }
            Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingExtraSmall))
        }
    }
}