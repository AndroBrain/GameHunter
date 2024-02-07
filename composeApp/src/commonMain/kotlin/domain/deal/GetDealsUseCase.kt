package domain.deal

import domain.core.UseCaseResult

class GetDealsUseCase(
    private val dealRepository: DealRepository,
) {
    suspend operator fun invoke(params: DealParams): UseCaseResult<List<DealModel>> =
        when (val result = dealRepository.getDeals(params)) {
            is UseCaseResult.Ok -> UseCaseResult.Ok(
                result.value
                    .distinctBy { model -> model.gameID }
                    .map { model -> model.copy(savings = model.savings.takeWhile { it != '.' }) }
            )

            else -> result
        }
}
