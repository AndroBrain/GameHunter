package domain.game.recently

import kotlinx.coroutines.flow.map

class GetRecentlyViewedUseCase(
    private val repository: RecentlyViewedRepository,
) {
    suspend operator fun invoke() = repository.get().map { it.reversed() }
}
