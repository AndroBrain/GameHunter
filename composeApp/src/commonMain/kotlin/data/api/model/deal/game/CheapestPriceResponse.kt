package data.api.model.deal.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheapestPriceResponse(
    @SerialName("date")
    val date: Int,
    @SerialName("price")
    val price: String,
)