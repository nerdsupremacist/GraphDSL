package io.quintero.graphdsl.codegen

import io.quintero.graphdsl.codegen.builder.CodeConvertible
import io.quintero.graphdsl.codegen.builder.KotlinCodeBuilder

object QueryBuilder : CodeConvertible<KotlinCodeBuilder> {

    override fun KotlinCodeBuilder.invoke() {
        +"""
        @DslMarker
        annotation class GraphQLDsl
            
        abstract class GraphQLBuilder<T> {
            @GraphQLDsl
            operator fun GraphQLFragment<T>.unaryPlus() {
                TODO()
            }
        }
        
        data class GraphQLQuery(val builder: GraphQLBuilder<*>)
        data class GraphQLFragment<T>(val name: String, val builder: GraphQLBuilder<T>)
        """.trimIndent()
    }

}