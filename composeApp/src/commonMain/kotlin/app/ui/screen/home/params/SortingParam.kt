package app.ui.screen.home.params

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
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
import app.ui.theme.icons.SortIcon
import domain.deal.DealSortingType

@Composable
fun SortingParam(
    type: DealSortingType,
    onChangeType: (DealSortingType) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            modifier = Modifier.widthIn(min = Resources.dimens.viewsSpacingExtraLarge).menuAnchor(),
            value = Resources.strings.sortingType(type),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            },
            leadingIcon = {
                Icon(imageVector = SortIcon, contentDescription = null)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DealSortingType.entries.forEach { type ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = Resources.strings.sortingType(type),
                            textAlign = TextAlign.End,
                        )
                    },
                    onClick = {
                        onChangeType(type)
                        expanded = false
                    }
                )
            }
        }
    }
}