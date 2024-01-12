package domain.shop

data class ShopModel(
    val images: ShopImagesModel,
    val isActive: Boolean,
    val storeID: String,
    val storeName: String,
)
