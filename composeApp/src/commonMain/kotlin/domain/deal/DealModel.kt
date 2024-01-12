package domain.deal

data class DealModel(
    val dealID: String,
    val dealRating: String,
    val gameID: String,
    val internalName: String,
    val isOnSale: Boolean,
    val lastChange: Int,
    val metacriticLink: String?,
    val metacriticScore: String,
    val normalPrice: String,
    val releaseDate: String?,
    val salePrice: String,
    val savings: String,
    val steamAppID: String?,
    val steamRatingCount: Int,
    val steamRatingPercent: Int,
    val steamRatingText: String?,
    val storeID: String,
    val thumb: String,
    val title: String,
)
