package data.datasource.deal.params

import domain.deal.DealSortingType

fun DealSortingType.toParam() = when (this) {
    DealSortingType.DEAL_RATING -> "DealRating"
    DealSortingType.TITLE -> "Title"
    DealSortingType.SAVINGS -> "Savings"
    DealSortingType.PRICE -> "Price"
    DealSortingType.REVIEWS -> "Reviews"
    DealSortingType.RELEASE -> "Release"
    DealSortingType.RECENT -> "Recent"
}
