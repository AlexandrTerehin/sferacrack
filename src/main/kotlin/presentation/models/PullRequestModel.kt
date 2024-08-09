package presentation.models

import domain.models.Decision

internal data class PullRequestModel(
    val id: Long,
    val title: String,
    val description: String,
    val authorLogin: String,
    val decision: Decision
)
