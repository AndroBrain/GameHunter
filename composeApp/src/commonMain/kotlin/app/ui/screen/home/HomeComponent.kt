package app.ui.screen.home

import app.util.BrowserOpener
import app.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import domain.deal.DealParams
import domain.deal.GetDealsUseCase
import domain.shop.GetShopsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface HomeComponent {
    val state: StateFlow<HomeState>

    fun getInitialDeals()
    fun getMoreDeals()
    fun openInBrowser(url: String)
}

class DefaultHomeComponent(
    context: ComponentContext,
    private val getDealsUseCase: GetDealsUseCase,
    private val getShopsUseCase: GetShopsUseCase,
    private val browserOpener: BrowserOpener,
) : HomeComponent, ComponentContext by context {
    private val _state = MutableStateFlow(HomeState())
    override val state = _state.asStateFlow()

    private val scope = coroutineScope(SupervisorJob())
    private var loadMoreJob: Job? = null

    init {
        scope.launch {
            val shops = getShopsUseCase()
            println(shops)
            _state.update { state -> state.copy(shops = shops) }
        }
    }

    override fun getInitialDeals() {
        scope.launch {
            val deals = getDealsUseCase(DealParams(pageNumber = 0))
            _state.update { state -> state.copy(deals = deals, isLoadingInitial = false, page = 1) }
        }
    }

    override fun getMoreDeals() {
        if (loadMoreJob != null) return
        _state.update { state -> state.copy(isLoadingMore = true) }
        loadMoreJob = scope.launch {
            val deals = getDealsUseCase(DealParams(pageNumber = state.value.page))
            _state.update { state ->
                state.copy(
                    deals = state.deals + deals,
                    isLoadingMore = false,
                    page = state.page + 1,
                )
            }
        }
        loadMoreJob?.invokeOnCompletion {
            loadMoreJob = null
        }
    }

    override fun openInBrowser(url: String) {
        browserOpener.openLink(url)
    }
}
