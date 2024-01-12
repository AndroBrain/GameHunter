package app.di

import data.datasource.deal.DealDataSource
import data.datasource.deal.KtorDealDataSource
import data.datasource.shop.KtorShopDataSource
import data.datasource.shop.ShopDataSource
import data.repository.deal.DefaultDealRepository
import data.repository.shop.DefaultShopRepository
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

    private fun provideDealRepository(): DealRepository =
        DefaultDealRepository(dealDataSource = provideDealDataSource())

    private fun provideDealDataSource(): DealDataSource =
        KtorDealDataSource()

    private fun provideShopRepository(): ShopRepository =
        DefaultShopRepository(shopDataSource = provideShopDataSource())

    private fun provideShopDataSource(): ShopDataSource =
        KtorShopDataSource()

}
