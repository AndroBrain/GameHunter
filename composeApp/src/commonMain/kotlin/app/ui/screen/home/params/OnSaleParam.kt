package app.ui.screen.home.params

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.ui.theme.Resources

@Composable
fun OnSaleParam(
    modifier: Modifier = Modifier,
    onSale: Boolean,
    onClick: () -> Unit,
) {
    FilterChip(
        modifier = modifier,
        selected = onSale,
        onClick = onClick,
        label = {
            Row {
                DealParamText(text = Resources.strings.onSaleOnly)
                if (onSale) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF3CB043),
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            }
        },
        colors = DealParamDefaults.colors(),
    )
}
