package app.ui.screen.home

import app.ui.dialog.game.DefaultGameComponent
import app.ui.dialog.game.GameComponent
import app.util.BrowserOpener
import app.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import domain.deal.DealParams
import domain.deal.DealSortingType
import domain.deal.GetDealsUseCase
import domain.deal.game.GetGameWithDealsUseCase
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
    val gameSlot: Value<ChildSlot<*, GameComponent>>

    fun getInitialDeals()
    fun getMoreDeals()
    fun openGame(gameID: String)
    fun changeSorting(type: DealSortingType)
}

class DefaultHomeComponent(
    context: ComponentContext,
    private val getDealsUseCase: GetDealsUseCase,
    private val getShopsUseCase: GetShopsUseCase,
    private val getGameWithDealsUseCase: GetGameWithDealsUseCase,
    private val browserOpener: BrowserOpener,
) : HomeComponent, ComponentContext by context {
    private val _state = MutableStateFlow(HomeState())
    override val state = _state.asStateFlow()

    private val scope = coroutineScope(SupervisorJob())
    private var loadMoreJob: Job? = null
    private var loadInitialJob: Job? = null

    private val gameNavigation = SlotNavigation<GameConfig>()
    private val _gameSlot =
        childSlot<GameConfig, GameComponent>(
            source = gameNavigation,
            serializer = null,
            handleBackButton = true,
            childFactory = { config, context ->
                DefaultGameComponent(
                    context = context,
                    gameID = config.gameID,
                    getGameWithDealsUseCase = getGameWithDealsUseCase,
                    dismiss = gameNavigation::dismiss,
                    browserOpener = browserOpener,
                    shops = state.value.shops.groupBy { it.storeID }.mapValues { it.value.first() },
                )
            }
        )
    override val gameSlot: Value<ChildSlot<*, GameComponent>> = _gameSlot

    init {
        scope.launch {
            val shops = getShopsUseCase()
            _state.update { state -> state.copy(shops = shops) }
        }
    }

    override fun getInitialDeals() {
        loadInitialJob?.cancel()
        _state.update { state -> state.copy(isLoadingInitial = true) }
        loadInitialJob = scope.launch {
            val deals = getDealsUseCase(DealParams(pageNumber = 0, state.value.sortingType))
            _state.update { state -> state.copy(deals = deals, isLoadingInitial = false, page = 1) }
        }
        loadInitialJob?.invokeOnCompletion {
            loadInitialJob = null
        }
    }

    override fun getMoreDeals() {
        if (loadMoreJob != null) return
        _state.update { state -> state.copy(isLoadingMore = true) }
        loadMoreJob = scope.launch {
            val deals =
                getDealsUseCase(DealParams(pageNumber = state.value.page, state.value.sortingType))
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

    override fun openGame(gameID: String) {
        gameNavigation.activate(GameConfig(gameID = gameID))
    }

    override fun changeSorting(type: DealSortingType) {
        val previousSortingType = state.value.sortingType
        _state.update { state ->
            state.copy(sortingType = type)
        }
        if (previousSortingType != type) {
            getInitialDeals()
        }
    }

    private data class GameConfig(
        val gameID: String,
    )
}
