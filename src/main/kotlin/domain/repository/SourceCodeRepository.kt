package domain.repository

import domain.models.PullRequest

internal interface SourceCodeRepository {

    suspend fun getListPullRequest(
        projectKey: String?,
        repoName: String?,
        prStatus: String?
    ): List<PullRequest>
}