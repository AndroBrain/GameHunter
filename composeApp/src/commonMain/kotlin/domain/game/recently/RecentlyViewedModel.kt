package domain.game.recently

data class RecentlyViewedModel(
    val title: String,
    val thumb: String,
    val steamRatingText: String?,
    val steamRatingPercent: Long?,
    val steamRatingCount: Long?,
    val metacriticScore: String?,
    val cheapSharkGameID: String?,
)
