package app.ui.dialog.notification

import app.util.DecimalFormatter
import app.util.Patterns
import app.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
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
    private val dismiss: () -> Unit,
    private val gameID: String,
) : NotificationComponent, ComponentContext by context {
    private val _state = MutableStateFlow(NotificationState(gameTitle = gameName))
    override val state: StateFlow<NotificationState> = _state.asStateFlow()

    private val scope = coroutineScope(SupervisorJob())
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
//        TODO start loading > send on backend > stop loading > dismiss
        _state.update { state -> state.copy(isLoading = true) }
        scope.launch {
            delay(3000L)
            _state.update { state -> state.copy(isLoading = false, dismiss = true) }
        }
    }
}
