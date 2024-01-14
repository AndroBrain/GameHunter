package domain.alert

data class SetAlertParams(
    val email: String,
    val gameID: String,
    val gameTitle: String,
    val price: Double,
)
