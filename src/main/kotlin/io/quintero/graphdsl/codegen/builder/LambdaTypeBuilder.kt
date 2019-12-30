package io.quintero.graphdsl.codegen.builder

class LambdaTypeBuilder {
    private var target: TypeIdentifierBuilder? = null
    private val arguments = mutableListOf<TypeBuilder>()
    private val returnType = TypeBuilder().apply { idenfitier { name = "Unit" } }

    fun target(init: TypeIdentifierBuilder.() -> Unit) {
        target = TypeIdentifierBuilder().apply(init)
    }

    fun addArgument(init: TypeBuilder.() -> Unit) {
        arguments += TypeBuilder().apply(init)
    }

    fun returnType(init: TypeBuilder.() -> Unit) {
        returnType.init()
    }

    fun build(): String {
        val lambda =
                "(${arguments.joinToString(", ") { it.build() } }) -> ${returnType.build()}"

        return target
                ?.let {
                    "${it.build()}.$lambda"
                } ?: lambda
    }
}