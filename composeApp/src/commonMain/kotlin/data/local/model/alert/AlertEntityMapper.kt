package data.local.model.alert

import com.androbrain.gamehunter.AlertEntity
import domain.alert.Alert

fun List<AlertEntity>.toModels() = map { it.toModel() }

fun AlertEntity.toModel() = Alert(
    email = email,
    gameTitle = gameTitle,
    gameID = gameId,
    price = price,
)