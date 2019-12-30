package io.quintero.graphdsl.schema

import io.quintero.graphdsl.codegen.QueryBuilder
import io.quintero.graphdsl.codegen.builder.CodeConvertible
import io.quintero.graphdsl.codegen.builder.KotlinCodeBuilder
import io.quintero.graphdsl.codegen.builder.function

data class Schema(
        val types: Iterable<Type>,
        val queryType: TypeReference
): CodeConvertible<KotlinCodeBuilder> {

    override fun KotlinCodeBuilder.invoke() {
        +QueryBuilder

        queryType.name?.let { queryTypeName ->
            function {
                name = "GraphQL"
                addArgument {
                    name = "init"
                    type {
                        lambda {
                            target {
                                name = "$queryTypeName.Builder"
                            }
                        }
                    }
                }
                returnType("GraphQLQuery")
                body {
                    +"TODO()"
                }
            }
        }

        types.filter { it.kind != Kind.SCALAR && !it.name.startsWith("__") }.forEach { +it }
    }

    companion object
}