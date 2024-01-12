package data.api.model.deal.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameInfoResponse(
    @SerialName("steamAppID")
    val steamAppID: String,
    @SerialName("thumb")
    val thumb: String,
    @SerialName("title")
    val title: String,
)