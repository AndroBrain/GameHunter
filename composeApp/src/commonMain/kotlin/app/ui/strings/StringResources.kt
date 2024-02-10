package app.ui.strings

import domain.deal.DealSortingType

interface StringResources {
    val appName: String
    val confirm: String

    val currencySign: String
    val steamReviews: String
    val steamRating: String

    val metacriticRating: String

    val cheapestPriceEver: String
    val getInTheseShops: String
    val gameNamePlaceholder: String

    val recentlyViewed: String

    fun sortingType(sortingType: DealSortingType): String
    val noLimit: String
    val maxPrice: String
    val onSaleOnly: String
    fun shops(count: Int, allShopsCount: Int): String
    val allShops: String

    val noGamesUnderFilter: String

    val notificationEmail: String
    val notificationMaxPrice: String
    val addNotification: String

    val invalidEmail: String

    val loadingError: String
    val reload: String

    val alerts: String
    val noAlertsInfo: String

    val errNetwork: String
    val errUnknown: String
}
