package data.datasource.game.recently.model

import com.androbrain.gamehunter.RecentlyViewedGameEntity
import domain.deal.DealModel
import domain.game.recently.RecentlyViewedModel

fun DealModel.toRecentlyViewedEntity() = RecentlyViewedGameEntity(
    thumb = thumb,
    title = title,
    steamRatingPercent = if (steamRatingPercent == 0) null else steamRatingPercent.toLong(),
    steamRatingCount = steamRatingCount.toLong(),
    steamRatingText = steamRatingText,
    metacriticRating = if (metacriticScore.toIntOrNull() != 0) metacriticScore else null,
    cheapSharkGameID = gameID,
)

fun List<RecentlyViewedGameEntity>.toModels() = map { it.toModel() }

fun RecentlyViewedGameEntity.toModel() = RecentlyViewedModel(
    title = title,
    thumb = thumb,
    steamRatingText = steamRatingText,
    steamRatingPercent = steamRatingPercent,
    steamRatingCount = steamRatingCount,
    metacriticScore = metacriticRating,
    cheapSharkGameID = cheapSharkGameID,
)
