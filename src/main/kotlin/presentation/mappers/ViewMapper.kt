package presentation.mappers

import core.settings.SferaSetting
import domain.models.Decision
import domain.models.PullRequest
import presentation.models.PullRequestModel

internal class ViewMapper {

    fun toUI(domain: List<PullRequest.Success>) = domain.map { toUI(it) }

    fun toUI(domain: PullRequest.Success) = PullRequestModel(
        id = domain.id,
        title = domain.title.orEmpty(),
        description = domain.description.orEmpty(),
        authorLogin = domain.authorLogin.orEmpty(),
        decision = domain.reviewers?.singleOrNull { reviewer ->
            SferaSetting.user?.login?.lowercase()?.startsWith(reviewer.login?.lowercase().orEmpty()) == true
        }?.decision ?: Decision.NULL
    )
}