package app.di

import domain.game.GetGamesUseCase

class DefaultSharedModule : SharedModule {
    override fun provideGetGamesUseCase(): GetGamesUseCase = GetGamesUseCase()
}
