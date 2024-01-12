package data.api.model.shop

import domain.shop.ShopImagesModel
import domain.shop.ShopModel

fun List<ShopResponse>.toModels() = map { it.toModel() }

fun ShopResponse.toModel() = ShopModel(
    images = images.toModel(),
    isActive = isActive == 1,
    storeID = storeID,
    storeName = storeName,
)

fun ShopImagesResponse.toModel() = ShopImagesModel(
    banner = banner, icon = icon, logo = logo,
)
