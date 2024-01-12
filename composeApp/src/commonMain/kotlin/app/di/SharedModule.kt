package app.di

import domain.game.GetGamesUseCase

interface SharedModule {
    fun provideGetGamesUseCase(): GetGamesUseCase
}
