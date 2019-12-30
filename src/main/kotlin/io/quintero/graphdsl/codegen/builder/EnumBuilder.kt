package io.quintero.graphdsl.codegen.builder

class EnumFunctionBuilder : TypeIdentifierBuilder() {
    private val cases = mutableListOf<String>()
    private val body = KotlinCodeBuilder()

    @KotlinCodeBuilderDsl
    fun addCase(name: String) {
        cases += name
    }

    @KotlinCodeBuilderDsl
    fun body(init: KotlinCodeBuilder.() -> Unit) {
        body.init()
    }

    override fun build(): String {
        return buildString {
            append("enum class ${super.build()} {")

            appendln()
            append(cases.joinToString(", ").prependIndent())
            appendln()
            append(";".prependIndent())
            appendln()
            append(body.build().prependIndent())
            appendln()

            append("}")
        }
    }
}

@KotlinCodeBuilderDsl
fun KotlinCodeBuilder.enum(init: EnumFunctionBuilder.() -> Unit) {
    +EnumFunctionBuilder().apply(init).build()
}