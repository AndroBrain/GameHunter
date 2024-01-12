package app.ui.screen.home

import domain.deal.DealModel

data class HomeState(
    val games: List<DealModel> = emptyList(),
)
