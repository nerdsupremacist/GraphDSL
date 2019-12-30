package io.quintero.graphdsl.codegen.builder

interface CodeBuilder<Self : CodeBuilder<Self>> {
    operator fun <T : CodeConvertible<Self>> T.unaryPlus()
}