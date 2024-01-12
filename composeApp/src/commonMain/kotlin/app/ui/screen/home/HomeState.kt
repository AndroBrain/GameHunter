package app.ui.screen.home

import domain.deal.DealModel

data class HomeState(
    val deals: List<DealModel> = emptyList(),
    val isLoadingInitial: Boolean = true,
    val isLoadingMore: Boolean = false,
    val page: Int = 0,
)
