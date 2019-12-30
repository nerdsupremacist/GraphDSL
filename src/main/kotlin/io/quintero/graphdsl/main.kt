package io.quintero.graphdsl

import io.quintero.graphdsl.codegen.builder.KotlinCodeBuilder
import io.quintero.graphdsl.schema.Schema
import io.quintero.graphdsl.schema.fetch

fun main() {
    val schema = Schema.fetch("https://countries.trevorblades.com/")
    print(KotlinCodeBuilder().apply { +schema }.build())
}