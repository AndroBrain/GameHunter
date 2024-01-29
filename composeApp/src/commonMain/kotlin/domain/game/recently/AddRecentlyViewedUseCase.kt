package domain.game.recently

import domain.deal.DealModel

class AddRecentlyViewedUseCase(
    private val repository: RecentlyViewedRepository,
) {
    suspend operator fun invoke(deal: DealModel) {
        repository.insertGame(deal)
    }
}
