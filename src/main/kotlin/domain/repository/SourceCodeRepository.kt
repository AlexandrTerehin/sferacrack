package domain.repository

import domain.models.PullRequest

internal interface SourceCodeRepository {

    suspend fun getListPullRequest(
        projectKey: String?,
        repoName: String?,
        prStatus: String?,
        cache: Boolean
    ): List<PullRequest>

    suspend fun isAuthorization(): Boolean
}