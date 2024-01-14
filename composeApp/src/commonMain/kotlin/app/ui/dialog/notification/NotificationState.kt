package app.ui.dialog.notification

data class NotificationState(
    val gameTitle: String,
    val email: String = "",
    val isEmailValid: Boolean = false,
    val price: Double = 0.0,
    val isLoading: Boolean = false,
    val dismiss: Boolean = false,
)
