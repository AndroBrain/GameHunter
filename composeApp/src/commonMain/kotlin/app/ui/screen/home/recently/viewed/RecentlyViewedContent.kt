package app.ui.screen.home.recently.viewed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.theme.Resources
import domain.game.recently.RecentlyViewedModel

@Composable
fun RecentlyViewedContent(
    modifier: Modifier = Modifier,
    items: List<RecentlyViewedModel>,
    onClick: (RecentlyViewedModel) -> Unit,
) {
    val cardModifier = Modifier.padding(end = Resources.dimens.viewsSpacingSmall)
        .width(Resources.dimens.dealMinSize)
    Column(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = Resources.dimens.screenSpacingSmall),
            text = "Recently viewed:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingSmall))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = Resources.dimens.screenSpacingSmall),
        ) {
            items(items) { item ->
                RecentlyViewedCard(
                    modifier = cardModifier,
                    model = item,
                    onClick = { onClick(item) },
                )
            }
        }
        Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingSmall))
        Divider()
        Spacer(modifier = Modifier.height(Resources.dimens.viewsSpacingSmall))
    }
}