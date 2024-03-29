package app.di

import domain.alert.DeleteAlertUseCase
import domain.alert.GetAlertEmailUseCase
import domain.alert.GetAlertsUseCase
import domain.alert.SetAlertUseCase
import domain.deal.GetDealsUseCase
import domain.deal.game.GetGameWithDealsUseCase
import domain.shop.GetShopsUseCase

interface SharedModule {
    fun provideGetGamesUseCase(): GetDealsUseCase
    fun provideGetShopsUseCase(): GetShopsUseCase
    fun provideGetGameWithDealsUseCase(): GetGameWithDealsUseCase
    fun provideSetAlertUseCase(): SetAlertUseCase
    fun provideGetAlertEmailUseCase(): GetAlertEmailUseCase
    fun provideGetAlertsUseCase(): GetAlertsUseCase
    fun provideDeleteAlertUseCase(): DeleteAlertUseCase
}
