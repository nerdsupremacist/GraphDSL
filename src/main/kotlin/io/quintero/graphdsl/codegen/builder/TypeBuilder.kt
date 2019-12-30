package io.quintero.graphdsl.codegen.builder

class TypeBuilder {
    private sealed class Builder {
        class Identifier(val builder: TypeIdentifierBuilder) : Builder()
        class Lambda(val builder: LambdaTypeBuilder) : Builder()


        fun build(): String {
            return when (this) {
                is Identifier -> builder.build()
                is Lambda -> builder.build()
            }
        }
    }

    private var builder: Builder = Builder.Identifier(TypeIdentifierBuilder())

    @KotlinCodeBuilderDsl
    fun idenfitier(init: TypeIdentifierBuilder.() -> Unit) {
        builder = Builder.Identifier(TypeIdentifierBuilder().apply(init))
    }

    @KotlinCodeBuilderDsl
    fun lambda(init: LambdaTypeBuilder.() -> Unit) {
        builder = Builder.Lambda(LambdaTypeBuilder().apply(init))
    }

    fun build(): String {
        return builder.build()
    }
}