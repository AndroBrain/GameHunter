package domain.alert

data class SetAlertParams(
    val email: String,
    val gameID: String,
    val price: Double,
)
