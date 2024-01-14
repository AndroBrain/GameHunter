package app.ui.screen.home.params

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.ui.theme.Resources

@Composable
fun OnSaleParam(
    modifier: Modifier = Modifier,
    onSale: Boolean,
    onClick: () -> Unit,
) {
    FilterChip(
        modifier = modifier.defaultMinSize(minHeight = TextFieldDefaults.MinHeight),
        selected = false,
        onClick = onClick,
        label = {
            Text(
                text = Resources.strings.onSaleOnly(onSale),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        shape = OutlinedTextFieldDefaults.shape,
    )
}
