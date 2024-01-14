package app.ui.screen.home.params

import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

object DealParamDefaults {
    @Composable
    fun colors() =
        FilterChipDefaults.filterChipColors(labelColor = MaterialTheme.colorScheme.onSurface)
}