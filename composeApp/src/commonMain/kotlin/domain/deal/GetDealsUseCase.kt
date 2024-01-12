package domain.deal

class GetDealsUseCase(
    private val dealRepository: DealRepository,
) {
    suspend operator fun invoke(): List<DealModel> = dealRepository.getDeals()
}
