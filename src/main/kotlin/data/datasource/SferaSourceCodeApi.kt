package data.datasource

import core.cache.SourceCodeCache
import core.consts.UrlConsts.SOURCE_CODE
import core.net.SourceCodeNet
import data.models.SourceCodePullRequestDto
import retrofit2.Call
import retrofit2.await
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal class SferaSourceCodeApi(private val net: SourceCodeNet) {

    suspend fun getListPullRequest(
        projectKey: String?,
        repoName: String?,
        prStatus: String?
    ): SourceCodePullRequestDto? {
        val service = net.get().create(PullRequestsService::class.java)
        return try {
            val pullRequest = service.listPullRequest(projectKey = projectKey, repoName = repoName, prStatus = prStatus)
            SourceCodeCache.pullRequestDto = pullRequest?.await()
            return SourceCodeCache.pullRequestDto
        } catch (th: Throwable) {
            null
        }
    }
}

private interface PullRequestsService {
    @GET("/${SOURCE_CODE}projects/{projectKey}/repos/{repoName}/pull-requests")
    fun listPullRequest(
        @Path("projectKey") projectKey: String?,
        @Path("repoName") repoName: String?,
        @Query("prStatus") prStatus: String?,
    ): Call<SourceCodePullRequestDto?>?
}