package io.quintero.graphdsl.codegen.builder

open class FunctionBuilder : CodeBuilder<FunctionBuilder> {
    lateinit var name: String
    var operator = false

    protected val annotations = mutableListOf<TypeIdentifierBuilder>()
    protected val arguments = mutableListOf<ArgumentBuilder>()
    protected val returnType = TypeIdentifierBuilder().apply { name = "Unit" }
    protected val body = KotlinCodeBuilder()

    override fun <T : CodeConvertible<FunctionBuilder>> T.unaryPlus() {
        invoke()
    }

    @KotlinCodeBuilderDsl
    fun addAnnotation(init: TypeIdentifierBuilder.() -> Unit) {
        annotations += TypeIdentifierBuilder().apply(init)
    }

    @KotlinCodeBuilderDsl
    fun addArgument(init: ArgumentBuilder.() -> Unit) {
        arguments += ArgumentBuilder().apply(init)
    }

    @KotlinCodeBuilderDsl
    fun returnType(init: TypeIdentifierBuilder.() -> Unit) {
        returnType.init()
    }

    @KotlinCodeBuilderDsl
    fun returnType(name: String) {
        returnType { this.name = name }
    }

    @KotlinCodeBuilderDsl
    fun body(init: KotlinCodeBuilder.() -> Unit) {
        body.init()
    }

    open fun build(): String {
        val arguments = this.arguments.joinToString(", ") { it.build() }
        return buildString {
            annotations.forEach {
                appendln("@${it.build()}")
            }
            if (operator) {
                append("operator ")
            }
            append("fun $name($arguments): ${returnType.build()} {")
            appendln()
            append(body.build().prependIndent())
            appendln()
            append("}")
        }
    }
}

@KotlinCodeBuilderDsl
fun KotlinCodeBuilder.function(init: FunctionBuilder.() -> Unit) {
    +FunctionBuilder().apply(init).build()
}