package domain.shop

import domain.core.UseCaseResult

class GetShopsUseCase(
    private val shopRepository: ShopRepository,
) {
    suspend operator fun invoke(): UseCaseResult<List<ShopModel>> =
        when (val result = shopRepository.getShops()) {
            is UseCaseResult.Ok -> UseCaseResult.Ok(result.value.filter { it.isActive })

            else -> result
        }
}
