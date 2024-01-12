package domain.deal

interface DealRepository {
    suspend fun getDeals(): List<DealModel>
}
