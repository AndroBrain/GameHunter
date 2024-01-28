package data.datasource.game.recently.model

import com.androbrain.gamehunter.RecentlyViewedGameEntity
import domain.deal.DealModel
import domain.game.recently.RecentlyViewedModel

fun DealModel.toRecentlyViewedEntity() = RecentlyViewedGameEntity(
    thumb = thumb,
    title = title,
    steamRatingCount = steamRatingCount.toLong(),
    steamRatingText = steamRatingText,
    metacriticRating = metacriticScore,
    cheapSharkGameID = gameID,
)

fun List<RecentlyViewedGameEntity>.toModels() = map { it.toModel() }

fun RecentlyViewedGameEntity.toModel() = RecentlyViewedModel(
    title = title,
    thumb = thumb,
    steamRating = steamRatingText,
    steamRatingCount = steamRatingCount,
    metacriticScore = metacriticRating,
    cheapSharkGameID = cheapSharkGameID,
)
