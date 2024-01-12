package domain.deal.game

import domain.deal.DealRepository

class GetGameWithDealsUseCase(
    private val dealRepository: DealRepository,
) {
    suspend operator fun invoke(gameID: String) = dealRepository.getGameWithDeals(gameID)
}
