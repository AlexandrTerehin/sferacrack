package data.models

internal sealed class PullRequestsResponse {
    data class Success(val dto: SourceCodePullRequestDto) : PullRequestsResponse()
    data class Error(val dto: SourceCodeErrorDto) : PullRequestsResponse()
}