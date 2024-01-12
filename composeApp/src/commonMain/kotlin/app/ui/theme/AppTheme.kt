package app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import app.ui.strings.EnStringResources
import app.ui.strings.StringResources

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppDimens provides Dimensions(),
        LocalStringResources provides EnStringResources()
    ) {
        MaterialTheme(
            content = content,
            colorScheme = darkColorScheme(),
        )
    }
}

private val LocalAppDimens = staticCompositionLocalOf {
    Dimensions()
}

private val LocalStringResources: ProvidableCompositionLocal<StringResources> =
    staticCompositionLocalOf {
        EnStringResources()
    }

object Resources {
    val dimens: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimens.current
    val strings: StringResources
        @Composable
        @ReadOnlyComposable
        get() = LocalStringResources.current
}
