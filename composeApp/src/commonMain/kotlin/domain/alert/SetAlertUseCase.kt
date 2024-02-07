package domain.alert

class SetAlertUseCase(
    private val alertRepository: AlertRepository,
) {
    suspend operator fun invoke(params: SetAlertParams) = alertRepository.setAlert(params)
}
