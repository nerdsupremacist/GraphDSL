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
        if (type.kind != Kind.NON_NULL) {
            defaultValue { +"null" }
        }
        type {
            idenfitier { +type }
        }
    }

}