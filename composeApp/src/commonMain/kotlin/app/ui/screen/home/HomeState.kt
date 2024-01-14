package app.ui.screen.home

import domain.deal.DealModel
import domain.deal.DealSortingType
import domain.shop.ShopModel

data class HomeState(
    val deals: List<DealModel> = emptyList(),
    val isLoadingInitial: Boolean = true,
    val isLoadingMore: Boolean = false,
    val page: Int = 0,
    val isFinalPage: Boolean = false,
    val shops: List<ShopModel> = emptyList(),
    val query: String = "",
    val sortingType: DealSortingType = DealSortingType.DEAL_RATING,
)
