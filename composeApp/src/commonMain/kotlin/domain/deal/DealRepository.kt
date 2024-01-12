package domain.deal

interface DealRepository {
    suspend fun getDeals(params: DealParams): List<DealModel>
}
