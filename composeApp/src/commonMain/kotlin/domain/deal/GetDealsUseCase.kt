package domain.deal

class GetDealsUseCase(
    private val dealRepository: DealRepository,
) {
    suspend operator fun invoke(params: DealParams): List<DealModel> =
        dealRepository.getDeals(params)
            .distinctBy { model -> model.gameID }
}
