package domain.game

import kotlinx.coroutines.delay

class GetGamesUseCase {
    suspend operator fun invoke(): List<GameModel> {
        delay(3000L)
        return listOf(
            GameModel(name = "1"),
            GameModel(name = "2"),
            GameModel(name = "3"),
            GameModel(name = "4"),
            GameModel(name = "5"),
        )
    }
}
