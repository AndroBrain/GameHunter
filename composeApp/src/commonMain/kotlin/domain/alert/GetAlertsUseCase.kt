package domain.alert

class GetAlertsUseCase(
    private val alertRepository: AlertRepository,
) {
    suspend operator fun invoke() = alertRepository.getAlerts()
}
