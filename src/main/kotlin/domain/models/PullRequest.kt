package domain.models

internal sealed class PullRequest {
    data class Success(
        val id: Long,
        val authorLogin: String?,
        val authorEmail: String?,
        val authorFirstName: String?,
        val authorMiddleName: String?,
        val authorLastName: String?,
        val authorFullName: String?,
        val title: String?,
        val description: String?,
        val closed: Boolean?,
        val reviewers: List<Reviewer>?
    ) : PullRequest()

    data class Error(val status: String) : PullRequest()
}

internal data class Reviewer(
    val login: String?,
    val decision: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val principalName: String?,
)