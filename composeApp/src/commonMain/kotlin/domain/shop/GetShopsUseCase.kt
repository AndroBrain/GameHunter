package domain.shop

class GetShopsUseCase(
    private val shopRepository: ShopRepository,
) {
    suspend operator fun invoke(): List<ShopModel> =
        shopRepository.getShops().filter { it.isActive }
}
