package app.ui.screen.root

import androidx.compose.runtime.Composable
import app.ui.theme.Resources
import domain.core.ResultErrorType

enum class Message {
    UNKNOWN,
    NETWORK_ERROR;

    @Composable
    fun asString() = when (this) {
        UNKNOWN -> Resources.strings.errUnknown
        NETWORK_ERROR -> Resources.strings.errNetwork
    }

    companion object {
        fun fromError(result: ResultErrorType) = when (result) {
            ResultErrorType.UNKNOWN -> UNKNOWN
            ResultErrorType.NETWORK -> NETWORK_ERROR
        }
    }
}
