package data.api.model.deal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DealResponse(
    @SerialName("dealID")
    val dealID: String,
    @SerialName("dealRating")
    val dealRating: String,
    @SerialName("gameID")
    val gameID: String,
    @SerialName("internalName")
    val internalName: String,
    @SerialName("isOnSale")
    val isOnSale: String,
    @SerialName("lastChange")
    val lastChange: Int,
    @SerialName("metacriticLink")
    val metacriticLink: String?,
    @SerialName("metacriticScore")
    val metacriticScore: String,
    @SerialName("normalPrice")
    val normalPrice: String,
    @SerialName("releaseDate")
    val releaseDate: Int,
    @SerialName("salePrice")
    val salePrice: String,
    @SerialName("savings")
    val savings: String,
    @SerialName("steamAppID")
    val steamAppID: String?,
    @SerialName("steamRatingCount")
    val steamRatingCount: Int,
    @SerialName("steamRatingPercent")
    val steamRatingPercent: Int,
    @SerialName("steamRatingText")
    val steamRatingText: String?,
    @SerialName("storeID")
    val storeID: String,
    @SerialName("thumb")
    val thumb: String,
    @SerialName("title")
    val title: String,
)