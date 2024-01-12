package app.ui.screen.home

import app.ui.util.coroutineScope
import com.arkivanov.decompose.ComponentContext
import domain.game.GetGamesUseCase
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface HomeComponent {
    val state: StateFlow<HomeState>

    fun getGames()
}

class DefaultHomeComponent(
    context: ComponentContext,
    private val getGamesUseCase: GetGamesUseCase,
) : HomeComponent, ComponentContext by context {
    private val _state = MutableStateFlow(HomeState())
    override val state = _state.asStateFlow()

    private val scope = coroutineScope(SupervisorJob())

    override fun getGames() {
        scope.launch {
            val games = getGamesUseCase()
            _state.update { state ->
                state.copy(games = games.map { GameDisplayable(it.name) })
            }
        }
    }
}
