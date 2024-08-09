package presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import core.consts.ProjectKeysConsts
import core.consts.UrlConsts
import core.settings.SferaSetting
import domain.di.DomainDI
import domain.models.PullRequest
import domain.usecase.GetListPullRequestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.mappers.ViewMapper
import presentation.models.PullRequestModel
import java.awt.Desktop
import java.net.URI
import kotlin.reflect.KClass

internal class SourceCodeViewModel(
    private val getListPullRequest: GetListPullRequestUseCase,
    private val viewMapper: ViewMapper
) : ViewModel() {

    private var cachePullRequest: List<PullRequest.Success>? = null

    private val _authorState = MutableStateFlow(false)
    val authorState = _authorState.asStateFlow()

    private val _reviewerState = MutableStateFlow(false)
    val reviewerState = _reviewerState.asStateFlow()

    private val _flowPullRequest = MutableSharedFlow<List<PullRequestModel>>()
    val flowPullRequest = _flowPullRequest.asSharedFlow()

    init {
        _count += 1
        println("count = $_count")
        viewModelScope.launch(Dispatchers.IO) {
            cachePullRequest =
                getListPullRequest(ProjectKeysConsts.PROJECT_KEY_ANDROID, ProjectKeysConsts.REPO_NAME_ANDROID)
                    .map { it as PullRequest.Success }

            cachePullRequest?.let {
                _flowPullRequest.emit(viewMapper.toUI(it))
            }
        }
    }

    fun openPullRequest(pullRequest: PullRequestModel) {
        Desktop.getDesktop().browse(URI(getLink(pullRequest = pullRequest)))
    }

    fun switchAuthor() {
        viewModelScope.launch(Dispatchers.IO) {
            val author = !_authorState.value

            _authorState.emit(author)
            cachePullRequest?.let { cache ->
                if (author) {
                    val result = cache.filter {
                        userLoginEquals(it.authorLogin) == true
                    }
                    _flowPullRequest.emit(viewMapper.toUI(result))
                } else {
                    _flowPullRequest.emit(viewMapper.toUI(cache))
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
                    val result = cache.filter { pr ->
                        pr.reviewers?.any { reviewerPR -> userLoginEquals(reviewerPR.login) == true } == true
                    }
                    _flowPullRequest.emit(viewMapper.toUI(result))
                } else {
                    _flowPullRequest.emit(viewMapper.toUI(cache))
                }
            }
        }
    }

    private fun userLoginEquals(login: String?) =
        SferaSetting.user?.login?.lowercase()?.startsWith(login?.lowercase().orEmpty())

    private fun getLink(pullRequest: PullRequestModel) =
        "${UrlConsts.SFERA}sourcecode/projects/${ProjectKeysConsts.PROJECT_KEY_ANDROID}/repos/${ProjectKeysConsts.REPO_NAME_ANDROID}/pulls/${pullRequest.id}"

    companion object {
        private var _count = 0

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return SourceCodeViewModel(
                    getListPullRequest = DomainDI.getListPullRequest(),
                    viewMapper = ViewMapper()
                ) as T
            }
        }
    }
}