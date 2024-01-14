package domain.deal

data class DealParams(
    val pageNumber: Int,
    val sortingType: DealSortingType,
    val query: String,
    val maxPrice: Int?,
)
