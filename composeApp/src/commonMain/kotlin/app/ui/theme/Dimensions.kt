package app.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val screenSpacingExtraSmall: Dp = 8.dp,
    val screenSpacingSmall: Dp = 12.dp,
    val screenSpacingMedium: Dp = 16.dp,
    val screenSpacingLarge: Dp = 32.dp,
    val screenSpacingExtraLarge: Dp = 48.dp,

    val viewsSpacingExtraSmall: Dp = 2.dp,
    val viewsSpacingSmall: Dp = 8.dp,
    val viewsSpacingMedium: Dp = 16.dp,
    val viewsSpacingLarge: Dp = 24.dp,
    val viewsSpacingExtraLarge: Dp = 32.dp,

    val dealMinSize: Dp = 320.dp,
    val dealImageWidth: Dp = 84.dp,
    val dealImageHeight: Dp = 84.dp,
)
