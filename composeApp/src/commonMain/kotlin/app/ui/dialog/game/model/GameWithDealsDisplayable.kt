package app.ui.dialog.game.model

import domain.deal.game.GameWithDealsModel
import domain.shop.ShopModel

data class GameWithDealsDisplayable(
    val game: GameDisplayable,
    val deals: List<GameDealDisplayable>,
) {
    constructor(
        model: GameWithDealsModel,
        shops: Map<String, ShopModel>,
    ) : this(
        game = GameDisplayable(
            title = model.info.title,
            thumb = model.info.thumb,
            cheapestPriceEver = model.cheapestPriceEver.price,
        ),
        deals = model.deals
            .asSequence()
            .filter { deal -> shops.containsKey(deal.storeID) }
            .map { deal ->
                GameDealDisplayable(
                    dealID = deal.dealID,
                    storeName = shops[deal.storeID]?.storeName.orEmpty(),
                    storeIcon = "https://www.cheapshark.com${shops[deal.storeID]?.images?.logo.orEmpty()}",
                    price = deal.price,
                )
            }
            .toList(),
    )
}
