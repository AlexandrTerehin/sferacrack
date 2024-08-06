package presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.consts.ProjectKeysConsts
import core.consts.UrlConsts
import core.settings.SferaSetting
import domain.models.PullRequest
import domain.usecase.GetListPullRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.awt.Desktop
import java.net.URI

internal class SourceCodeViewModel(
    private val getListPullRequest: GetListPullRequest
) : ViewModel() {

    private val user = SferaSetting.getUser()
    private var cachePullRequest: List<PullRequest>? = null

    private val _authorState = MutableStateFlow(false)
    val authorState = _authorState.asStateFlow()

    private val _reviewerState = MutableStateFlow(false)
    val reviewerState = _reviewerState.asStateFlow()

    private val _flowPullRequest = MutableSharedFlow<List<PullRequest>>()
    val flowPullRequest = _flowPullRequest.asSharedFlow()

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            cachePullRequest =
                getListPullRequest(ProjectKeysConsts.PROJECT_KEY_ANDROID, ProjectKeysConsts.REPO_NAME_ANDROID)
            cachePullRequest?.let {
                _flowPullRequest.emit(it)
            }
        }
    }

    fun openPullRequest(pullRequest: PullRequest) {
        Desktop.getDesktop().browse(URI(getLink(pullRequest = pullRequest)))
    }

    fun switchAuthor() {
        viewModelScope.launch(Dispatchers.IO) {
            val author = !_authorState.value

            _authorState.emit(author)
            cachePullRequest?.let { cache ->
                if (author) {
                    _flowPullRequest.emit(cache.filter {
                        userLoginEquals(it.authorLogin)
                    })
                } else {
                    _flowPullRequest.emit(cache)
                }
            }
        }
    }

    fun switchReviewer() {
        viewModelScope.launch(Dispatchers.IO) {
            val reviewer = !_reviewerState.value

            _reviewerState.emit(reviewer)
            cachePullRequest?.let { cache ->
                if (reviewer) {
                    _flowPullRequest.emit(cache.filter { pr ->
                        pr.reviewers?.any { reviewerPR -> userLoginEquals(reviewerPR.login) } == true
                    })
                } else {
                    _flowPullRequest.emit(cache)
                }
            }
        }
    }

    private fun userLoginEquals(login: String?) = user.login.lowercase().startsWith(login?.lowercase().orEmpty())

    private fun getLink(pullRequest: PullRequest) =
        "${UrlConsts.SFERA}sourcecode/projects/${ProjectKeysConsts.PROJECT_KEY_ANDROID}/repos/${ProjectKeysConsts.REPO_NAME_ANDROID}/pulls/${pullRequest.id}"
}