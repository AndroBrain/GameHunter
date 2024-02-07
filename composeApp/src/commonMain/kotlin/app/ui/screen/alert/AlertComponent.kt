package app.ui.screen.alert

import app.ui.screen.root.Message
import app.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import domain.alert.Alert
import domain.alert.DeleteAlertParams
import domain.alert.DeleteAlertUseCase
import domain.alert.GetAlertsUseCase
import domain.core.fold
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface AlertComponent {
    val state: StateFlow<AlertState>

    fun deleteAlert(alert: Alert)
    fun onClose()
}

class DefaultAlertComponent(
    context: ComponentContext,
    private val getAlertsUseCase: GetAlertsUseCase,
    private val deleteAlertUseCase: DeleteAlertUseCase,
    private val close: () -> Unit,
    private val setMessage: (Message) -> Unit,
) : AlertComponent, ComponentContext by context {
    private val _state = MutableStateFlow(AlertState())
    override val state = _state.asStateFlow()

    private val scope = coroutineScope(SupervisorJob())

    init {
        scope.launch {
            getAlertsUseCase().onEach { alerts ->
                println(alerts)
                _state.update { state -> state.copy(alerts = alerts) }
            }.launchIn(this)
        }
    }

    override fun deleteAlert(alert: Alert) {
        scope.launch {
            deleteAlertUseCase(DeleteAlertParams(email = alert.email, gameID = alert.gameID)).fold(
                onOk = {},
                onError = { setMessage(Message.fromError(it.type)) },
            )
        }
    }

    override fun onClose() {
        close()
    }
}
