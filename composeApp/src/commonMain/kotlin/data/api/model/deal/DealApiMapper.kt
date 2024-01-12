package data.api.model.deal

import domain.deal.DealModel
import io.ktor.util.date.GMTDate

fun List<DealResponse>.toModels() = map { it.toModel() }

fun DealResponse.toModel(): DealModel {
    val date = GMTDate(releaseDate.toLong() * 1000)
    val release = if (releaseDate == 0) {
        null
    } else {
        buildString {
            append("${date.dayOfMonth.toString().padStart(2, '0')} ")
            append("${date.month.value} ")
            append(date.year.toString().padStart(4, '0'))
        }
    }
    return DealModel(
        dealID = dealID,
        dealRating = dealRating,
        gameID = gameID,
        internalName = internalName,
        isOnSale = isOnSale.toInt() == 1,
        lastChange = lastChange,
        metacriticLink = metacriticLink,
        metacriticScore = metacriticScore,
        normalPrice = normalPrice,
        releaseDate = release,
        salePrice = salePrice,
        savings = savings,
        steamAppID = steamAppID,
        steamRatingCount = steamRatingCount,
        steamRatingPercent = steamRatingPercent,
        steamRatingText = steamRatingText,
        storeID = storeID,
        thumb = thumb,
        title = title,
    )
}
