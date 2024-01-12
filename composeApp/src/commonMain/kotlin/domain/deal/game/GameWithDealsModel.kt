package domain.deal.game

data class GameWithDealsModel(
    val cheapestPriceEver: CheapestPriceModel,
    val deals: List<GameDealModel>,
    val info: GameInfoModel,
)
