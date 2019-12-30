package io.quintero.graphdsl.codegen.builder

class ArgumentBuilder : CodeBuilder<ArgumentBuilder> {
    lateinit var name: String
    private val type = TypeBuilder()
    private var defaultValue: KotlinCodeBuilder? = null

    override fun <T : CodeConvertible<ArgumentBuilder>> T.unaryPlus() {
        invoke()
    }

    @KotlinCodeBuilderDsl
    fun type(init: TypeBuilder.() -> Unit) {
        type.init()
    }

    @KotlinCodeBuilderDsl
    fun defaultValue(init: KotlinCodeBuilder.() -> Unit) {
        defaultValue = KotlinCodeBuilder().apply(init)
    }

    @KotlinCodeBuilderDsl
    fun build(): String {
        defaultValue?.let { return "$name: ${type.build()} = ${it.build()}" }
        return "$name: ${type.build()}"
    }
}