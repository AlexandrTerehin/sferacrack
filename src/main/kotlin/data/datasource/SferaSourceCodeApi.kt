package data.datasource

import com.google.gson.Gson
import core.cache.SourceCodeCache
import core.consts.ProjectKeysConsts
import core.consts.UrlConsts.SOURCE_CODE
import core.net.SourceCodeNet
import data.models.PullRequestsResponse
import data.models.SourceCodeErrorDto
import data.models.SourceCodePullRequestDto
import domain.enums.PRStatus
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.await
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal class SferaSourceCodeApi(private val net: SourceCodeNet) {

    suspend fun getListPullRequest(
        projectKey: String?,
        repoName: String?,
        prStatus: String?,
        cache: Boolean,
    ): PullRequestsResponse {
        if (cache && SourceCodeCache.pullRequestDto != null) {
            SourceCodeCache.pullRequestDto?.let {
                return PullRequestsResponse.Success(dto = it)
            }
        }

        val service = net.get().create(PullRequestsService::class.java)
        val pullRequest = service.listPullRequest(projectKey = projectKey, repoName = repoName, prStatus = prStatus)

        return try {
            SourceCodeCache.pullRequestDto = pullRequest.await()
            SourceCodeCache.pullRequestDto?.let {
                return PullRequestsResponse.Success(dto = it)
            } ?: PullRequestsResponse.Error(dto = SourceCodeErrorDto.getEmpty())
        } catch (th: Throwable) {
            SourceCodeCache.pullRequestDto = null
            val httpException = th as? HttpException
            httpException?.response()?.errorBody()?.let { error ->
                val dto = Gson().fromJson(error.charStream(), SourceCodeErrorDto::class.java)
                return PullRequestsResponse.Error(dto = dto)
            } ?: PullRequestsResponse.Error(dto = SourceCodeErrorDto.getEmpty())
        }
    }

    suspend fun isAuthorization(): Boolean {
        val response = getListPullRequest(
            ProjectKeysConsts.PROJECT_KEY_ANDROID,
            ProjectKeysConsts.REPO_NAME_ANDROID,
            PRStatus.OPEN.get(),
            cache = false
        )
        return when (response) {
            is PullRequestsResponse.Success -> true
            is PullRequestsResponse.Error -> false
        }
    }
}

private interface PullRequestsService {
    @GET("/${SOURCE_CODE}projects/{projectKey}/repos/{repoName}/pull-requests")
    fun listPullRequest(
        @Path("projectKey") projectKey: String?,
        @Path("repoName") repoName: String?,
        @Query("prStatus") prStatus: String?,
    ): Call<SourceCodePullRequestDto>
}