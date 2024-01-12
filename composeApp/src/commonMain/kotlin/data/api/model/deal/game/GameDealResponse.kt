package data.api.model.deal.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDealResponse(
    @SerialName("dealID")
    val dealID: String,
    @SerialName("price")
    val price: String,
    @SerialName("retailPrice")
    val retailPrice: String,
    @SerialName("savings")
    val savings: String,
    @SerialName("storeID")
    val storeID: String,
)