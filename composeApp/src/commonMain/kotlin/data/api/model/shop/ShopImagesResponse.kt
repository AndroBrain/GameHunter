package data.api.model.shop

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShopImagesResponse(
    @SerialName("banner")
    val banner: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("logo")
    val logo: String,
)