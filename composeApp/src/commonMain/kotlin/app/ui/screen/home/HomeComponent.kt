package app.ui.screen.home

import app.util.BrowserOpener
import app.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import domain.deal.GetDealsUseCase
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface HomeComponent {
    val state: StateFlow<HomeState>

    fun getDeals()
    fun openInBrowser(url: String)
}

class DefaultHomeComponent(
    context: ComponentContext,
    private val getDealsUseCase: GetDealsUseCase,
    private val browserOpener: BrowserOpener,
) : HomeComponent, ComponentContext by context {
    private val _state = MutableStateFlow(HomeState())
    override val state = _state.asStateFlow()

    private val scope = coroutineScope(SupervisorJob())

    override fun getDeals() {
        scope.launch {
            val games = getDealsUseCase()
            _state.update { state -> state.copy(deals = games, isLoadingInitial = false) }
        }
    }

    override fun openInBrowser(url: String) {
        browserOpener.openLink(url)
    }
}
