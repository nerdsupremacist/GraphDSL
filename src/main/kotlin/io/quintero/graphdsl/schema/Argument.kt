package io.quintero.graphdsl.schema

import io.quintero.graphdsl.codegen.builder.ArgumentBuilder
import io.quintero.graphdsl.codegen.builder.CodeConvertible

data class Argument(
    val name: String,
    val defaultValue: String?,
    val type: TypeReference
): CodeConvertible<ArgumentBuilder> {

    override fun ArgumentBuilder.invoke() {
        name = this@Argument.name
        type {
            idenfitier { +type }
        }
    }

}