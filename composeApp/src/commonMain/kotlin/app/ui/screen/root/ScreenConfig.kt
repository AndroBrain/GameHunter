package app.ui.screen.root

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenConfig {
    @Serializable
    data object Home : ScreenConfig()
}
