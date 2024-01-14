package data.datasource.deal.params

fun Boolean.toParam() = if (this) {
    "1"
} else {
    "0"
}
