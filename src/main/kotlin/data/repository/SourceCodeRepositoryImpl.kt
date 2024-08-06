package data.repository

import data.datasource.SferaSourceCodeApi
import data.mappers.SourceCodeMapper
import domain.models.PullRequest
import domain.repository.SourceCodeRepository

internal class SourceCodeRepositoryImpl(
    private val api: SferaSourceCodeApi,
    private val mapper: SourceCodeMapper
) : SourceCodeRepository {

    override suspend fun getListPullRequest(projectKey: String?, repoName: String?, prStatus: String?): List<PullRequest> {
        api.getListPullRequest(projectKey, repoName, prStatus)?.let {
            return mapper.toDomain(it)
        } ?: return emptyList()
    }
}