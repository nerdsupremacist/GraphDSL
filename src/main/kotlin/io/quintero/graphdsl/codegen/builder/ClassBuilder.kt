package io.quintero.graphdsl.codegen.builder

class ClassBuilder : TypeIdentifierBuilder() {
    var isDataClass = false
    private var superClass: TypeBuilder? = null
    private val arguments = mutableListOf<ArgumentBuilder>()
    private val body = KotlinCodeBuilder()

    @KotlinCodeBuilderDsl
    fun superClass(init: TypeBuilder.() -> Unit) {
        superClass = TypeBuilder().apply(init)
    }

    @KotlinCodeBuilderDsl
    fun argument(init: ArgumentBuilder.() -> Unit) {
        arguments += ArgumentBuilder().apply(init)
    }

    @KotlinCodeBuilderDsl
    fun body(init: KotlinCodeBuilder.() -> Unit) {
        body.init()
    }

    override fun build(): String {
        val arguments = this.arguments.joinToString(", ") { "val ${it.build()}" }
        return buildString {
            if (isDataClass) append("data ")

            append("class ${super.build()}")

            if (arguments.isNotEmpty()) {
                append("($arguments)")
            }

            superClass?.let { append(": ${it.build()}()") }
            append(" {")

            appendln()
            append(body.build().prependIndent())
            appendln()

            append("}")
        }
    }
}

@KotlinCodeBuilderDsl
fun KotlinCodeBuilder.kotlinClass(init: ClassBuilder.() -> Unit) {
    +ClassBuilder().apply(init).build()
}