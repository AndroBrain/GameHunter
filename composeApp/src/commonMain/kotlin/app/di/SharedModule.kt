package app.di

import domain.deal.GetDealsUseCase
import domain.shop.GetShopsUseCase

interface SharedModule {
    fun provideGetGamesUseCase(): GetDealsUseCase
    fun provideGetShopsUseCase(): GetShopsUseCase
}
