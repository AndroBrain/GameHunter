package app.di

import data.datasource.alert.KtorRemoteAlertDataSource
import data.datasource.alert.RemoteAlertDataSource
import data.datasource.deal.DealDataSource
import data.datasource.deal.KtorDealDataSource
import data.datasource.shop.KtorShopDataSource
import data.datasource.shop.ShopDataSource
import data.repository.alert.DefaultAlertRepository
import data.repository.deal.DefaultDealRepository
import data.repository.shop.DefaultShopRepository
import domain.alert.AlertRepository
import domain.alert.SetAlertUseCase
import domain.deal.DealRepository
import domain.deal.GetDealsUseCase
import domain.deal.game.GetGameWithDealsUseCase
import domain.shop.GetShopsUseCase
import domain.shop.ShopRepository

class DefaultSharedModule : SharedModule {
    override fun provideGetGamesUseCase() =
        GetDealsUseCase(dealRepository = provideDealRepository())

    override fun provideGetShopsUseCase(): GetShopsUseCase =
        GetShopsUseCase(shopRepository = provideShopRepository())

    override fun provideGetGameWithDealsUseCase(): GetGameWithDealsUseCase =
        GetGameWithDealsUseCase(dealRepository = provideDealRepository())

    override fun provideSetAlertUseCase(): SetAlertUseCase =
        SetAlertUseCase(alertRepository = provideAlertRepository())

    private fun provideDealRepository(): DealRepository =
        DefaultDealRepository(dealDataSource = provideDealDataSource())

    private fun provideDealDataSource(): DealDataSource =
        KtorDealDataSource()

    private fun provideShopRepository(): ShopRepository =
        DefaultShopRepository(shopDataSource = provideShopDataSource())

    private fun provideShopDataSource(): ShopDataSource =
        KtorShopDataSource()

    private fun provideAlertRepository(): AlertRepository =
        DefaultAlertRepository(remoteDataSource = provideRemoteAlertDataSource())

    private fun provideRemoteAlertDataSource(): RemoteAlertDataSource =
        KtorRemoteAlertDataSource()
}
