package app.ui.screen.home

import app.ui.screen.home.params.shop.ShopDisplayable
import domain.deal.DealModel
import domain.deal.DealSortingType
import domain.game.recently.RecentlyViewedModel

data class HomeState(
    val deals: List<DealModel> = emptyList(),
    val isLoadingInitial: Boolean = true,
    val isLoadingMore: Boolean = false,
    val page: Int = 0,
    val isFinalPage: Boolean = false,
    val recentlyViewed: List<RecentlyViewedModel> = emptyList(),
    val shops: List<ShopDisplayable> = emptyList(),
    val query: String = "",
    val sortingType: DealSortingType = DealSortingType.DEAL_RATING,
    val maxPrice: Int? = null,
    val onSale: Boolean = false,
    val isInError: Boolean = false,

    val paramsFirstItemIndex: Int = 0,
    val paramsFirstItemOffset: Int = 0,
)
