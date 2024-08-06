package core.di

import core.net.SourceCodeNet

internal object CoreDI {
    private val sourceCodeNet = SourceCodeNet()

    fun getSourceCodeNet() = sourceCodeNet
}