package app.ui.screen.root

import androidx.compose.runtime.Composable
import app.ui.theme.Resources

enum class Message {
    NETWORK_ERROR;

    @Composable
    fun asString() = when (this) {
        NETWORK_ERROR -> Resources.strings.errNetwork
    }
}
