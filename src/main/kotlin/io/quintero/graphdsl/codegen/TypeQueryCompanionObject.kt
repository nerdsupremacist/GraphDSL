package io.quintero.graphdsl.codegen

import io.quintero.graphdsl.codegen.builder.CodeConvertible
import io.quintero.graphdsl.codegen.builder.KotlinCodeBuilder

data class TypeQueryCompanionObject(val typeName: String) : CodeConvertible<KotlinCodeBuilder> {
    override fun KotlinCodeBuilder.invoke() {
        +"""
            companion object {
                operator fun invoke(name: String, init: Builder.() -> Unit): GraphQLFragment<$typeName> {
                    return GraphQLFragment(name, Builder().apply(init))
                }
            }
        """.trimIndent()
    }
}