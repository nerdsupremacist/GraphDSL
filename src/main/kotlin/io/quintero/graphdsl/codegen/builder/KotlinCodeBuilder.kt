package io.quintero.graphdsl.codegen.builder

class KotlinCodeBuilder : CodeBuilder<KotlinCodeBuilder> {
    private val code = mutableListOf<String>()

    @KotlinCodeBuilderDsl
    operator fun String.unaryPlus() {
        code += this
    }

    @KotlinCodeBuilderDsl
    override operator fun <T: CodeConvertible<KotlinCodeBuilder>> T.unaryPlus() {
        invoke()
    }

    fun build(): String {
        return code.joinToString("\n\n")
    }
}