package io.quintero.graphdsl.schema

import io.quintero.graphdsl.codegen.builder.ArgumentBuilder
import io.quintero.graphdsl.codegen.builder.CodeConvertible

data class InputField(
        val name: String,
        val type: TypeReference,
        val defaultValue: String?
): CodeConvertible<ArgumentBuilder> {

    override fun ArgumentBuilder.invoke() {
        name = this@InputField.name
        type {
            idenfitier {
                +type
            }
        }
    }

}