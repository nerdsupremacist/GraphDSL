package io.quintero.graphdsl.codegen.builder

interface CodeConvertible<Builder : CodeBuilder<Builder>> {
    operator fun Builder.invoke()
}