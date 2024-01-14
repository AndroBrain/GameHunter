package app.ui.dialog.game

import app.ui.dialog.game.model.GameWithDealsDisplayable
import app.ui.dialog.notification.DefaultNotificationComponent
import app.ui.dialog.notification.NotificationComponent
import app.util.BrowserOpener
import app.util.coroutineScope
import app.util.dealLink
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import domain.alert.SetAlertUseCase
import domain.deal.game.GetGameWithDealsUseCase
import domain.shop.ShopModel
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface GameComponent {
    val state: StateFlow<GameState>
    val notificationSlot: Value<ChildSlot<*, NotificationComponent>>
    fun onDismiss()
    fun openInStore(dealID: String)
    fun setupAlert()
}

class DefaultGameComponent(
    context: ComponentContext,
    private val dismiss: () -> Unit,
    private val gameID: String,
    private val getGameWithDealsUseCase: GetGameWithDealsUseCase,
    private val setAlertUseCase: SetAlertUseCase,
    private val browserOpener: BrowserOpener,
    private val shops: Map<String, ShopModel>,
) : GameComponent, ComponentContext by context {
    private val _state = MutableStateFlow(GameState())
    override val state = _state.asStateFlow()

    private val notificationNavigation = SlotNavigation<NotificationConfig>()
    private val _notificationSlot =
        childSlot<NotificationConfig, NotificationComponent>(
            source = notificationNavigation,
            serializer = null,
            handleBackButton = true,
            childFactory = { config, context ->
                DefaultNotificationComponent(
                    context = context,
                    gameName = config.gameTitle,
                    gameID = config.gameID,
                    dismiss = notificationNavigation::dismiss,
                    setAlertUseCase = setAlertUseCase,
                )
            }
        )
    override val notificationSlot: Value<ChildSlot<*, NotificationComponent>> = _notificationSlot

    private val scope = coroutineScope(SupervisorJob())

    init {
        scope.launch {
            val gameWithDeals = getGameWithDealsUseCase(gameID)
            _state.update { state ->
                state.copy(
                    gameWithDeals = GameWithDealsDisplayable(gameWithDeals, shops),
                )
            }
        }
    }

    override fun onDismiss() {
        dismiss()
    }

    override fun openInStore(dealID: String) {
        browserOpener.openLink(dealLink(dealID))
    }

    override fun setupAlert() {
        notificationNavigation.activate(
            NotificationConfig(
                gameID = gameID,
                gameTitle = state.value.gameWithDeals?.game?.title.orEmpty(),
            )
        )
    }

    private data class NotificationConfig(
        val gameID: String,
        val gameTitle: String,
    )
}
