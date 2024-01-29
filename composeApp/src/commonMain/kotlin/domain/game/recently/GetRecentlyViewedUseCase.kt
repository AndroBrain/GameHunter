package domain.game.recently

class GetRecentlyViewedUseCase(
    private val repository: RecentlyViewedRepository,
) {
    suspend operator fun invoke() = repository.get()
}
