package io.quintero.graphdsl.codegen.builder

class ExtensionFunctionBuilder : FunctionBuilder() {
    private val target = TypeIdentifierBuilder()

    @KotlinCodeBuilderDsl
    fun target(init: TypeIdentifierBuilder.() -> Unit) {
        target.init()
    }

    @KotlinCodeBuilderDsl
    fun target(name: String) {
        target {
            this.name = name
        }
    }

    override fun build(): String {
        val arguments = this.arguments.joinToString(", ") { it.build() }
        return buildString {
            annotations.forEach {
                appendln("@${it.build()}")
            }
            if (operator) {
                append("operator ")
            }
            append("fun ${target.build()}.$name($arguments): ${returnType.build()} {")
            appendln()
            append(body.build().prependIndent())
            appendln()
            append("}")
        }
    }
}

@KotlinCodeBuilderDsl
fun KotlinCodeBuilder.extensionFunction(init: ExtensionFunctionBuilder.() -> Unit) {
    +ExtensionFunctionBuilder().apply(init).build()
}