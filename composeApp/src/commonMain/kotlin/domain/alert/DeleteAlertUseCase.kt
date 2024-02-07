package domain.alert

class DeleteAlertUseCase(
    private val alertRepository: AlertRepository,
) {
    suspend operator fun invoke(params: DeleteAlertParams) = alertRepository.deleteAlert(params)
}
