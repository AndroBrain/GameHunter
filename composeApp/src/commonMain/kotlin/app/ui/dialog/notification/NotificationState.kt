package app.ui.dialog.notification

import app.ui.screen.root.Message

data class NotificationState(
    val gameTitle: String,
    val email: String = "",
    val isEmailValid: Boolean = false,
    val price: Double = 0.0,
    val isLoading: Boolean = false,
    val dismiss: Boolean = false,
    val errorMessage: Message? = null,
)
