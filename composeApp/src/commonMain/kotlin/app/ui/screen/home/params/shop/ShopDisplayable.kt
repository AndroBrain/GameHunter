package app.ui.screen.home.params.shop

import domain.shop.ShopImagesModel
import domain.shop.ShopModel

data class ShopDisplayable(
    val images: ShopImagesModel,
    val storeID: String,
    val storeName: String,
    val checked: Boolean,
) {
    constructor(model: ShopModel) : this(
        images = model.images,
        storeID = model.storeID,
        storeName = model.storeName,
        checked = true,
    )
}
