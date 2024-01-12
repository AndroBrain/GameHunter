package data.api.model.shop

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShopResponse(
    @SerialName("images")
    val images: ShopImagesResponse,
    @SerialName("isActive")
    val isActive: Int,
    @SerialName("storeID")
    val storeID: String,
    @SerialName("storeName")
    val storeName: String,
)