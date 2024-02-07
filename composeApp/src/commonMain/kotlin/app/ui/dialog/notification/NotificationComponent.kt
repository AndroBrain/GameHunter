package app.ui.dialog.notification

import app.ui.screen.root.Message
import app.util.DecimalFormatter
import app.util.Patterns
import app.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import domain.alert.GetAlertEmailUseCase
import domain.alert.SetAlertParams
import domain.alert.SetAlertUseCase
import domain.core.fold
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface NotificationComponent {
    val state: StateFlow<NotificationState>
    fun onDismiss()
    fun changeEmail(email: String)
    fun changePrice(price: String)
    fun setAlert()
}

class DefaultNotificationComponent(
    context: ComponentContext,
    gameName: String,
    private val setAlertUseCase: SetAlertUseCase,
    private val getAlertEmailUseCase: GetAlertEmailUseCase,
    private val dismiss: () -> Unit,
    private val gameID: String,
    private val setMessage: (Message) -> Unit,
) : NotificationComponent, ComponentContext by context {
    private val _state = MutableStateFlow(NotificationState(gameTitle = gameName))
    override val state: StateFlow<NotificationState> = _state.asStateFlow()

    private val scope = coroutineScope(SupervisorJob())

    init {
        scope.launch {
            val email = getAlertEmailUseCase()
            changeEmail(email.orEmpty())
        }
    }

    override fun onDismiss() {
        dismiss()
    }

    override fun changeEmail(email: String) {
        _state.update { state ->
            state.copy(
                email = email,
                isEmailValid = email.matches(Patterns.emailRegex),
            )
        }
    }

    override fun changePrice(price: String) {
        _state.update { state -> state.copy(price = DecimalFormatter.format(price).toDouble()) }
    }

    override fun setAlert() {
        _state.update { state -> state.copy(isLoading = true) }
        scope.launch {
            val currentState = state.value
            setAlertUseCase(
                SetAlertParams(
                    email = currentState.email,
                    gameID = gameID,
                    gameTitle = currentState.gameTitle,
                    price = currentState.price,
                )
            ).fold(
                onOk = {
                    _state.update { state -> state.copy(isLoading = false, dismiss = true) }
                }, onError = {
                    _state.update { state -> state.copy(isLoading = false) }
                    setMessage(Message.fromError(it.type))
                }
            )
        }
    }
}
