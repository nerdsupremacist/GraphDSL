package io.quintero.graphdsl.codegen.builder

open class TypeIdentifierBuilder : CodeBuilder<TypeIdentifierBuilder> {
    lateinit var name: String
    private val arguments = mutableListOf<TypeIdentifierBuilder>()

    override fun <T : CodeConvertible<TypeIdentifierBuilder>> T.unaryPlus() {
        invoke()
    }

    @KotlinCodeBuilderDsl
    fun addGeneric(init: TypeIdentifierBuilder.() -> Unit) {
        arguments += TypeIdentifierBuilder().apply(init)
    }

    @KotlinCodeBuilderDsl
    fun addGeneric(name: String) {
        addGeneric {
            this.name = name
        }
    }

    open fun build(): String {
        if (arguments.isEmpty()) return name
        val generics = arguments.joinToString(", ") { it.build() }
        return "$name<${generics}>"
    }
}