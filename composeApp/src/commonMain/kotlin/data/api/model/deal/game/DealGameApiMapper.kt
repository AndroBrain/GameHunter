package data.api.model.deal.game

import domain.deal.game.CheapestPriceModel
import domain.deal.game.GameDealModel
import domain.deal.game.GameInfoModel
import domain.deal.game.GameWithDealsModel

fun GameWithDealsResponse.toModel() =
    GameWithDealsModel(
        cheapestPriceEver = cheapestPriceEver.toModel(),
        deals = deals.toModels(),
        info = info.toModel(),
    )

fun CheapestPriceResponse.toModel() =
    CheapestPriceModel(date = date, price = price)

fun List<GameDealResponse>.toModels() = map { it.toModel() }

fun GameDealResponse.toModel() =
    GameDealModel(
        dealID = dealID,
        price = price,
        retailPrice = retailPrice,
        savings = savings,
        storeID = storeID,
    )

fun GameInfoResponse.toModel() =
    GameInfoModel(steamAppID = steamAppID, thumb = thumb, title = title)
