package app.ui.dialog.game

import app.ui.dialog.game.model.GameWithDealsDisplayable
import app.util.BrowserOpener
import app.util.coroutineScope
import app.util.dealLink
import com.arkivanov.decompose.ComponentContext
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
    fun onDismiss()
    fun openInStore(dealID: String)
}

class DefaultGameComponent(
    context: ComponentContext,
    private val dismiss: () -> Unit,
    private val gameID: String,
    private val getGameWithDealsUseCase: GetGameWithDealsUseCase,
    private val browserOpener: BrowserOpener,
    private val shops: Map<String, ShopModel>,
) : GameComponent, ComponentContext by context {

    private val _state = MutableStateFlow(GameState())
    override val state = _state.asStateFlow()

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
}
