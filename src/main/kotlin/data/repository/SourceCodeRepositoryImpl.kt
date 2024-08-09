package data.repository

import androidx.compose.ui.res.useResource
import com.google.gson.Gson
import data.datasource.SferaSourceCodeApi
import data.mappers.SourceCodeMapper
import data.models.SourceCodePullRequestDto
import domain.models.PullRequest
import domain.repository.SourceCodeRepository

internal class SourceCodeRepositoryImpl(
    private val api: SferaSourceCodeApi,
    private val mapper: SourceCodeMapper
) : SourceCodeRepository {

    override suspend fun getListPullRequest(
        projectKey: String?,
        repoName: String?,
        prStatus: String?,
        cache: Boolean
    ): List<PullRequest> {
        return mapper.toDomain(getTestData())
        /*api.getListPullRequest(projectKey, repoName, prStatus, cache)?.let {
            return mapper.toDomain(it)
        } ?: return emptyList()*/
    }

    // todo test data
    private fun getTestData(): SourceCodePullRequestDto {
        useResource("prTest.json") { stream ->
            val textJson = stream.bufferedReader().use { it.readText() }
            return Gson().fromJson(textJson, SourceCodePullRequestDto::class.java)
        }
    }
}