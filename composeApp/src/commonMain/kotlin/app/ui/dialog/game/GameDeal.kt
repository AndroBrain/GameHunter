package app.ui.dialog.game

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import app.ui.composable.image.AsyncImage
import app.ui.composable.modifier.scaleOnClick
import app.ui.dialog.game.model.GameDealDisplayable
import app.ui.theme.Resources

@Composable
fun GameDeal(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    deal: GameDealDisplayable,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${deal.price}${Resources.strings.currencySign} ",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            AsyncImage(
                modifier = Modifier.size(Resources.dimens.shopIconSize),
                url = deal.storeIcon
            )
        }
        val interactionSource = remember { MutableInteractionSource() }
        Button(
            modifier = Modifier.scaleOnClick(interactionSource = interactionSource),
            onClick = onClick,
            interactionSource = interactionSource,
        ) {
            Text(text = deal.storeName)
        }
    }
}
