package app.ui.screen.alert

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.ui.theme.Resources
import domain.alert.Alert

@Composable
fun AlertCard(
    modifier: Modifier = Modifier,
    alert: Alert,
    onDelete: () -> Unit,
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(Resources.dimens.viewsSpacingSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Text(
                modifier = Modifier.weight(0.55F),
                text = alert.gameTitle,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Text(
                modifier = Modifier.weight(0.3F),
                text = alert.email,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            Text(
                modifier = Modifier.weight(0.15f),
                text = "${alert.price}${Resources.strings.currencySign}",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.width(Resources.dimens.viewsSpacingSmall))
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}