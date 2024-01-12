package app.ui.screen.home

import domain.deal.DealModel
import domain.shop.ShopModel

data class HomeState(
    val deals: List<DealModel> = emptyList(),
    val isLoadingInitial: Boolean = true,
    val isLoadingMore: Boolean = false,
    val page: Int = 0,
    val shops: List<ShopModel> = emptyList(),
)
