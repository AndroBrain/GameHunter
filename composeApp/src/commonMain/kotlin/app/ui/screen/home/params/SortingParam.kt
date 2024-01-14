package app.ui.screen.home.params

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.ui.theme.Resources
import domain.deal.DealSortingType

@Composable
fun SortingParam(
    modifier: Modifier = Modifier,
    type: DealSortingType,
    onChangeType: (DealSortingType) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        FilterChip(
            selected = true,
            modifier = Modifier.menuAnchor(),
            onClick = {},
            label = { DealParamText(text = Resources.strings.sortingType(type)) },
            colors = DealParamDefaults.colors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DealSortingType.entries.forEach { type ->
                DropdownMenuItem(
                    text = { Text(text = Resources.strings.sortingType(type)) },
                    onClick = {
                        onChangeType(type)
                        expanded = false
                    }
                )
            }
        }
    }
}
