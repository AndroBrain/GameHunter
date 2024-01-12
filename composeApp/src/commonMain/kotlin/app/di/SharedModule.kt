package app.di

import domain.deal.GetDealsUseCase

interface SharedModule {
    fun provideGetGamesUseCase(): GetDealsUseCase
}
