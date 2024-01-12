package app.di

import data.datasource.deal.DealDataSource
import data.datasource.deal.KtorDealDataSource
import data.repository.deal.DefaultDealRepository
import domain.deal.DealRepository
import domain.deal.GetDealsUseCase

class DefaultSharedModule : SharedModule {
    override fun provideGetGamesUseCase() =
        GetDealsUseCase(dealRepository = provideDealRepository())

    private fun provideDealDataSource(): DealDataSource =
        KtorDealDataSource()

    private fun provideDealRepository(): DealRepository =
        DefaultDealRepository(dealDataSource = provideDealDataSource())
}
