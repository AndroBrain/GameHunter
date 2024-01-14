package app.ui.screen.home.params

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import app.ui.theme.Resources

@Composable
fun MaxPriceParam(
    modifier: Modifier = Modifier,
    maxPrice: Int?,
    onMaxPriceChanged: (Int?) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor().widthIn(min = Resources.dimens.viewsSpacingSmall),
            value = if (maxPrice == null) {
                "${Resources.strings.maxPrice}${Resources.strings.noLimit}"
            } else {
                "${Resources.strings.maxPrice}${Resources.strings.currencySign}$maxPrice"
            },
            onValueChange = {},
            readOnly = true,
            singleLine = true,
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            maxPriceValues.forEach { price ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = if (price == null) {
                                Resources.strings.noLimit
                            } else {
                                "${Resources.strings.currencySign}${price}"
                            },
                            textAlign = TextAlign.End,
                        )
                    },
                    onClick = {
                        onMaxPriceChanged(price)
                        expanded = false
                    }
                )
            }
        }
    }
}

private val maxPriceValues = listOf(
    5, 10, 15, 20, 25, 30, 35, 40, 45, null
)
