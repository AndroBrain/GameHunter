package data.api.model.deal

import domain.deal.DealModel

fun List<DealResponse>.toModels() = map { it.toModel() }

fun DealResponse.toModel() = DealModel(
    dealID = dealID,
    dealRating = dealRating,
    gameID = gameID,
    internalName = internalName,
    isOnSale = isOnSale,
    lastChange = lastChange,
    metacriticLink = metacriticLink,
    metacriticScore = metacriticScore,
    normalPrice = normalPrice,
    releaseDate = releaseDate,
    salePrice = salePrice,
    savings = savings,
    steamAppID = steamAppID,
    steamRatingCount = steamRatingCount,
    steamRatingPercent = steamRatingPercent,
    steamRatingText = steamRatingText,
    storeID = storeID,
    thumb = thumb,
    title = title,
)
