package domain.di

import core.di.CoreDI
import data.datasource.SferaSourceCodeApi
import data.di.DataDI
import data.repository.SourceCodeRepositoryImpl
import domain.repository.SourceCodeRepository
import domain.usecase.GetListPullRequest

internal object DomainDI {
    private val sferaSourceCodeApi: SferaSourceCodeApi = SferaSourceCodeApi(net = CoreDI.getSourceCodeNet())
    private val sourceCodeRepository: SourceCodeRepository =
        SourceCodeRepositoryImpl(api = sferaSourceCodeApi, mapper = DataDI.getSourceCodeMapper())
    private val getListPullRequest: GetListPullRequest = GetListPullRequest(repository = sourceCodeRepository)

    fun getListPullRequest() = getListPullRequest
}