package data.models

import com.google.gson.annotations.SerializedName

internal data class SourceCodePullRequestDto(
    val status: String,
    @SerializedName("request_id")
    val requestId: String,
    val data: List<PullRequestDto>,
)

internal data class PullRequestDto(
    val assignees: List<AssigneeDto>,
    val author: AuthorDto,
    val closed: Boolean,
    @SerializedName("closed_at")
    val closedAt: String,
    val conflicts: List<String>,
    val counters: CountersDto,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    @SerializedName("group_assignees")
    val groupAssignees: List<GroupAssigneeDto>?,
    val id: Long,
    @SerializedName("merge_base")
    val mergeBase: String,
    @SerializedName("merge_status")
    val mergeStatus: String,
    @SerializedName("merged_at")
    val mergedAt: String,
    val permissions: PermissionsDto,
    @SerializedName("source_branch")
    val sourceBranch: String,
    @SerializedName("source_branch_delete_status")
    val sourceBranchDeleteStatus: String,
    @SerializedName("source_head")
    val sourceHead: String,
    val status: String,
    @SerializedName("target_branch")
    val targetBranch: String,
    val title: String,
    @SerializedName("untracked_files")
    val untrackedFiles: List<String>,
    @SerializedName("updated_at")
    val updatedAt: String,
)

internal data class AssigneeDto(
    val decision: String?,
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    val login: String?,
    @SerializedName("middle_name")
    val middleName: String?,
    @SerializedName("principal_name")
    val principalName: String?,
)

internal data class AuthorDto(
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("last_name")
    val lastName: String,
    val login: String,
    @SerializedName("middle_name")
    val middleName: String,
    @SerializedName("principal_name")
    val principalName: String,
)

internal data class CountersDto(
    @SerializedName("accepted_tech_debt_count")
    val acceptedTechDebtCount: Long,
    @SerializedName("comments_count")
    val commentsCount: Long,
    @SerializedName("potential_tech_debt_count")
    val potentialTechDebtCount: Long,
)

internal data class GroupAssigneeDto(
    val assignees: List<AssigneeInGroupDto>?,
    val id: Long?,
    @SerializedName("min_approvals")
    val minApprovals: Long?,
    val name: String?,
)

data class AssigneeInGroupDto(
    val login: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("middle_name")
    val middleName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("full_name")
    val fullName: String?,
    val email: String?,
    @SerializedName("principal_name")
    val principalName: String?,
    val decision: String?,
)

internal data class PermissionsDto(
    @SerializedName("can_add_assignee")
    val canAddAssignee: Boolean,
    @SerializedName("can_close")
    val canClose: Boolean,
    @SerializedName("can_force_merge")
    val canForceMerge: Boolean,
    @SerializedName("can_merge")
    val canMerge: Boolean,
    @SerializedName("can_remove_assignee")
    val canRemoveAssignee: Boolean,
    @SerializedName("can_reopen")
    val canReopen: Boolean,
    @SerializedName("can_set_decision")
    val canSetDecision: Boolean,
)

internal data class PageDto(
    @SerializedName("next_cursor")
    val nextCursor: String,
    @SerializedName("prev_cursor")
    val prevCursor: String,
)
