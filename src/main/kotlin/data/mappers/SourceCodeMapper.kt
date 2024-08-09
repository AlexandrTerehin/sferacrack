package data.mappers

import data.models.PullRequestDto
import data.models.SourceCodePullRequestDto
import domain.models.Decision
import domain.models.PullRequest
import domain.models.Reviewer

internal class SourceCodeMapper {
    fun toDomain(dto: SourceCodePullRequestDto): List<PullRequest> {
        return dto.data.map {
            PullRequest.Success(
                id = it.id,
                authorLogin = it.author.login,
                authorEmail = it.author.email,
                authorFirstName = it.author.firstName,
                authorMiddleName = it.author.middleName,
                authorLastName = it.author.lastName,
                authorFullName = it.author.fullName,
                title = it.title,
                description = it.description,
                closed = it.closed,
                reviewers = getReviewers(it)
            )
        }
    }

    private fun getReviewers(dto: PullRequestDto): List<Reviewer> {
        val reviewers = mutableListOf<Reviewer>()
        dto.assignees.forEach { ass ->
            reviewers.add(
                Reviewer(
                    login = ass.login,
                    decision = ass.decision?.let { decision -> Decision.entries.singleOrNull { it.key == decision } }
                        ?: Decision.NULL,
                    email = ass.email,
                    firstName = ass.firstName,
                    lastName = ass.lastName,
                    middleName = ass.middleName,
                    principalName = ass.principalName,
                )
            )
        }

        dto.groupAssignees?.forEach { group ->
            group.assignees?.forEach { ass ->
                reviewers.add(
                    Reviewer(
                        login = ass.login,
                        decision = ass.decision?.let { decision -> Decision.entries.singleOrNull { it.key == decision } }
                            ?: Decision.NULL,
                        email = ass.email,
                        firstName = ass.firstName,
                        lastName = ass.lastName,
                        middleName = ass.middleName,
                        principalName = ass.principalName,
                    )
                )
            }
        }
        return reviewers
    }
}