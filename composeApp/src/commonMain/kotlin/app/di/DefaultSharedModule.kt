package app.di

import com.androbrain.gamehunter.GameHunterDatabase
import data.datasource.alert.KtorRemoteAlertDataSource
import data.datasource.alert.LocalAlertDataSource
import data.datasource.alert.RemoteAlertDataSource
import data.datasource.alert.SqlDelightLocalAlertDataSource
import data.datasource.deal.DealDataSource
import data.datasource.deal.KtorDealDataSource
import data.datasource.shop.KtorShopDataSource
import data.datasource.shop.ShopDataSource
import data.repository.alert.DefaultAlertRepository
import data.repository.deal.DefaultDealRepository
import data.repository.shop.DefaultShopRepository
import domain.alert.AlertRepository
import domain.alert.GetAlertEmailUseCase
import domain.alert.SetAlertUseCase
import domain.deal.DealRepository
import domain.deal.GetDealsUseCase
import domain.deal.game.GetGameWithDealsUseCase
import domain.shop.GetShopsUseCase
import domain.shop.ShopRepository

class DefaultSharedModule(
    private val db: GameHunterDatabase,
) : SharedModule {
    override fun provideGetGamesUseCase() =
        GetDealsUseCase(dealRepository = provideDealRepository())

    override fun provideGetShopsUseCase(): GetShopsUseCase =
        GetShopsUseCase(shopRepository = provideShopRepository())

    override fun provideGetGameWithDealsUseCase(): GetGameWithDealsUseCase =
        GetGameWithDealsUseCase(dealRepository = provideDealRepository())

    override fun provideSetAlertUseCase(): SetAlertUseCase =
        SetAlertUseCase(alertRepository = provideAlertRepository())

    override fun provideGetAlertEmailUseCase(): GetAlertEmailUseCase =
        GetAlertEmailUseCase(alertRepository = provideAlertRepository())

    private fun provideDealRepository(): DealRepository =
        DefaultDealRepository(dealDataSource = provideDealDataSource())

    private fun provideDealDataSource(): DealDataSource =
        KtorDealDataSource()

    private fun provideShopRepository(): ShopRepository =
        DefaultShopRepository(shopDataSource = provideShopDataSource())

    private fun provideShopDataSource(): ShopDataSource =
        KtorShopDataSource()

    private fun provideAlertRepository(): AlertRepository =
        DefaultAlertRepository(
            remoteDataSource = provideRemoteAlertDataSource(),
            localDataSource = provideLocalAlertDataSource(),
        )

    private fun provideRemoteAlertDataSource(): RemoteAlertDataSource =
        KtorRemoteAlertDataSource()

    private fun provideLocalAlertDataSource(): LocalAlertDataSource =
        SqlDelightLocalAlertDataSource(db = db)
}
