package domain.game.recently

data class RecentlyViewedModel(
    val title: String,
    val thumb: String,
    val steamRating: String?,
    val steamRatingCount: Long?,
    val metacriticScore: String?,
    val cheapSharkGameID: String?,
)
