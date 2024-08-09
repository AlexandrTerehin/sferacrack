package domain.usecase

import domain.enums.PRStatus
import domain.repository.SourceCodeRepository

internal class GetListPullRequestUseCase(
    private val repository: SourceCodeRepository
) {
    suspend operator fun invoke(
        projectKey: String?,
        repoName: String?,
        prStatus: String? = PRStatus.OPEN.get(),
        cache: Boolean = true,
    ) = repository.getListPullRequest(projectKey = projectKey, repoName = repoName, prStatus = prStatus, cache = cache)
}