package app.ui.strings

import domain.deal.DealSortingType

class EnStringResources : StringResources {
    override val appName: String
        get() = "GameHunter"
    override val currencySign: String
        get() = "$"
    override val steamReviews: String
        get() = "steam reviews"
    override val dealRating: String
        get() = "Deal rating: "
    override val steamRating: String
        get() = " | Steam rating: "
    override val cheapestPriceEver: String
        get() = " — cheapest price ever"
    override val getInTheseShops: String
        get() = "Get the game in these shops:"
    override val gameNamePlaceholder: String
        get() = "Game name example…"

    override fun sortingType(sortingType: DealSortingType): String = when (sortingType) {
        DealSortingType.DEAL_RATING -> "Deal rating"
        DealSortingType.TITLE -> "Title"
        DealSortingType.SAVINGS -> "% Saved on deal"
        DealSortingType.PRICE -> "Price"
        DealSortingType.REVIEWS -> "Steam reviews"
        DealSortingType.RELEASE -> "Release"
        DealSortingType.RECENT -> "Recent"
    }

    override val noLimit: String
        get() = "No limit"

    override val maxPrice: String
        get() = "Price limit: "

    override fun onSaleOnly(onSale: Boolean): String =
        "On sale only ${if (onSale) "✔️" else "❌"}"

    override val notificationEmail: String
        get() = "Email"
    override val notificationMaxPrice: String
        get() = "Price limit"
    override val sendNotification: String
        get() = "Send alerts"
    override val invalidEmail: String
        get() = "Invalid email"
}
