package domain.deal.game

data class GameDealModel(
    val dealID: String,
    val price: String,
    val retailPrice: String,
    val savings: String,
    val storeID: String,
)
