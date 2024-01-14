package app.ui.screen.alert

import domain.alert.Alert

data class AlertState(
    val alerts: List<Alert> = emptyList(),
)
