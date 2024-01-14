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

    fun sortingType(sortingType: DealSortingType): String
    val noLimit: String
    val maxPrice: String
}
