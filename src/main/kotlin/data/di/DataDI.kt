package data.di

import data.mappers.SourceCodeMapper

internal object DataDI {
    private val sourceCodeMapper: SourceCodeMapper = SourceCodeMapper()

    fun getSourceCodeMapper() = sourceCodeMapper
}