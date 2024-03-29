package app.ui.strings

import domain.deal.DealSortingType

interface StringResources {
    val appName: String
    val currencySign: String
    val steamReviews: String
    val dealRating: String
    val steamRating: String

    val cheapestPriceEver: String
    val getInTheseShops: String
    val gameNamePlaceholder: String

    fun sortingType(sortingType: DealSortingType): String
    val noLimit: String
    val maxPrice: String
    fun onSaleOnly(onSale: Boolean): String

    val notificationEmail: String
    val notificationMaxPrice: String
    val sendNotification: String

    val invalidEmail: String

    val loadingError: String
    val reload: String

    val alerts: String
}
