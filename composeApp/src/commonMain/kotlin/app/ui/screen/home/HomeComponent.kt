package app.ui.screen.home

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface HomeComponent {
    val state: StateFlow<HomeState>
}

class DefaultHomeComponent(
    context: ComponentContext,
) : HomeComponent, ComponentContext by context {
    private val _state = MutableStateFlow(HomeState())
    override val state = _state.asStateFlow()
}
