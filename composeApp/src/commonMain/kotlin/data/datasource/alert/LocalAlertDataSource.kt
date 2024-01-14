package data.datasource.alert

interface LocalAlertDataSource {
    suspend fun insertEmail(email: String)
    suspend fun getEmail(): String?
}
