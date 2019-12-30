package io.quintero.graphdsl.schema

import io.quintero.graphdsl.codegen.builder.CodeConvertible
import io.quintero.graphdsl.codegen.builder.TypeIdentifierBuilder

data class TypeReference(
        val name: String?,
        val kind: Kind?,
        val ofType: TypeReference?
): CodeConvertible<TypeIdentifierBuilder> {

    override fun TypeIdentifierBuilder.invoke() {
        name = kotlinType
    }

}