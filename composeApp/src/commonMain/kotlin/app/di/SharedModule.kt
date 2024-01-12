package app.di

import domain.deal.GetDealsUseCase
import domain.deal.game.GetGameWithDealsUseCase
import domain.shop.GetShopsUseCase

interface SharedModule {
    fun provideGetGamesUseCase(): GetDealsUseCase
    fun provideGetShopsUseCase(): GetShopsUseCase
    fun provideGetGameWithDealsUseCase(): GetGameWithDealsUseCase
}
