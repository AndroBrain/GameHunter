package domain.alert

class GetAlertEmailUseCase(
    private val alertRepository: AlertRepository,
) {
    suspend operator fun invoke() = alertRepository.getEmail()
}
