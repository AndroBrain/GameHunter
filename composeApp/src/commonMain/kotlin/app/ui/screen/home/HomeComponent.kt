package app.ui.screen.home

import app.ui.dialog.game.DefaultGameComponent
import app.ui.dialog.game.GameComponent
import app.ui.screen.root.Message
import app.util.BrowserOpener
import app.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import domain.alert.GetAlertEmailUseCase
import domain.alert.SetAlertUseCase
import domain.deal.DealModel
import domain.deal.DealParams
import domain.deal.DealSortingType
import domain.deal.GetDealsUseCase
import domain.deal.game.GetGameWithDealsUseCase
import domain.game.recently.AddRecentlyViewedUseCase
import domain.game.recently.GetRecentlyViewedUseCase
import domain.shop.GetShopsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface HomeComponent {
    val state: StateFlow<HomeState>
    val gameSlot: Value<ChildSlot<*, GameComponent>>

    fun getInitialDeals()
    fun getMoreDeals()
    fun openAlerts()
    fun openGame(deal: DealModel)
    fun openRecentlyViewed(gameID: String?)
    fun changeQuery(query: String)
    fun changeSorting(type: DealSortingType)
    fun changeMaxPrice(maxPrice: Int?)
    fun changeOnSale()
}

class DefaultHomeComponent(
    context: ComponentContext,
    private val navigateToAlerts: () -> Unit,
    private val getDealsUseCase: GetDealsUseCase,
    private val getShopsUseCase: GetShopsUseCase,
    private val getGameWithDealsUseCase: GetGameWithDealsUseCase,
    private val getAlertEmailUseCase: GetAlertEmailUseCase,
    private val setAlertUseCase: SetAlertUseCase,
    private val getRecentlyViewedUseCase: GetRecentlyViewedUseCase,
    private val addRecentlyViewedUseCase: AddRecentlyViewedUseCase,
    private val browserOpener: BrowserOpener,
    private val setMessage: (Message) -> Unit,
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
                    setAlertUseCase = setAlertUseCase,
                    getAlertEmailUseCase = getAlertEmailUseCase,
                )
            }
        )
    override val gameSlot: Value<ChildSlot<*, GameComponent>> = _gameSlot

    init {
        scope.launch {
            val shops = getShopsUseCase()
            _state.update { state -> state.copy(shops = shops) }
        }
        scope.launch {
            getRecentlyViewedUseCase().onEach { recentlyViewed ->
                _state.update { state -> state.copy(recentlyViewed = recentlyViewed) }
            }.launchIn(this)
        }
    }

    override fun getInitialDeals() {
        loadInitialJob?.cancel()
        _state.update { state -> state.copy(isLoadingInitial = true, isInError = false) }
        loadInitialJob = scope.launch {
            val currentState = state.value
            val deals = getDealsUseCase(
                DealParams(
                    pageNumber = 0,
                    sortingType = currentState.sortingType,
                    query = currentState.query,
                    maxPrice = currentState.maxPrice,
                    onSale = currentState.onSale,
                )
            )
            if (deals == null) {
                _state.update { state ->
                    state.copy(isInError = true)
                }
            } else {
                _state.update { state ->
                    state.copy(
                        deals = deals,
                        isLoadingInitial = false,
                        page = 1,
                        isFinalPage = false,
                        isInError = false,
                    )
                }
            }
        }
        loadInitialJob?.invokeOnCompletion {
            loadInitialJob = null
        }
    }

    override fun getMoreDeals() {
        if (loadMoreJob != null || state.value.isFinalPage) return
        _state.update { state -> state.copy(isLoadingMore = true) }
        loadMoreJob = scope.launch {
            val currentState = state.value
            val deals = getDealsUseCase(
                DealParams(
                    pageNumber = currentState.page,
                    sortingType = currentState.sortingType,
                    query = currentState.query,
                    maxPrice = currentState.maxPrice,
                    onSale = currentState.onSale,
                )
            )
            if (deals != null) {
                _state.update { state ->
                    state.copy(
                        deals = state.deals + deals,
                        isLoadingMore = false,
                        page = state.page + 1,
                        isFinalPage = deals.isEmpty(),
                    )
                }
            }
        }
        loadMoreJob?.invokeOnCompletion {
            loadMoreJob = null
        }
    }

    override fun openAlerts() {
        navigateToAlerts()
    }

    override fun openGame(deal: DealModel) {
        scope.launch { addRecentlyViewedUseCase(deal) }
        gameNavigation.activate(GameConfig(gameID = deal.gameID))
    }

    override fun openRecentlyViewed(gameID: String?) {
        if (gameID == null) {
            // TODO display an error message that there currently are no deals for the game
        } else {
            gameNavigation.activate(GameConfig(gameID = gameID))
        }
    }

    override fun changeQuery(query: String) {
        _state.update { state -> state.copy(query = query) }
    }

    override fun changeSorting(type: DealSortingType) {
        val previousSortingType = state.value.sortingType
        _state.update { state -> state.copy(sortingType = type) }
        if (previousSortingType != type) {
            getInitialDeals()
        }
    }

    override fun changeMaxPrice(maxPrice: Int?) {
        val previousPrice = state.value.maxPrice
        _state.update { state -> state.copy(maxPrice = maxPrice) }
        if (previousPrice != maxPrice) {
            getInitialDeals()
        }
    }

    override fun changeOnSale() {
        _state.update { state -> state.copy(onSale = !state.onSale) }
        getInitialDeals()
    }

    private data class GameConfig(
        val gameID: String,
    )
}
