package domain.di

import core.net.SourceCodeNet
import data.datasource.SferaSourceCodeApi
import data.di.DataDI
import data.repository.SourceCodeRepositoryImpl
import domain.repository.SourceCodeRepository
import domain.usecase.CheckAuthorizationUseCase
import domain.usecase.GetListPullRequestUseCase

internal object DomainDI {
    private var sferaSourceCodeApi: SferaSourceCodeApi = SferaSourceCodeApi(net = SourceCodeNet())
    private var sourceCodeRepository: SourceCodeRepository =
        SourceCodeRepositoryImpl(api = sferaSourceCodeApi, mapper = DataDI.getSourceCodeMapper())
    private val getListPullRequest: GetListPullRequestUseCase =
        GetListPullRequestUseCase(repository = sourceCodeRepository)

    fun getListPullRequest() = getListPullRequest
    fun checkAuthorizationUseCase() = createCheckAuthorizationUseCase()

    private fun createCheckAuthorizationUseCase(): CheckAuthorizationUseCase {
        val sferaSourceCodeApi = SferaSourceCodeApi(net = SourceCodeNet())
        val sourceCodeRepository =
            SourceCodeRepositoryImpl(api = sferaSourceCodeApi, mapper = DataDI.getSourceCodeMapper())
        return CheckAuthorizationUseCase(repository = sourceCodeRepository)
    }
}