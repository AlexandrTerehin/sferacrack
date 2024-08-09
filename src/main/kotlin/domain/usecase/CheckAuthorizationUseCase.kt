package domain.usecase

import domain.repository.SourceCodeRepository

internal class CheckAuthorizationUseCase(
    private val repository: SourceCodeRepository
) {
    suspend operator fun invoke() = repository.isAuthorization()
}