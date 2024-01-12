package data.api.model.deal.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameWithDealsReponse(
    @SerialName("cheapestPriceEver")
    val cheapestPriceEver: CheapestPriceResponse,
    @SerialName("deals")
    val deals: List<GameDealResponse>,
    @SerialName("info")
    val info: GameInfoResponse,
)
