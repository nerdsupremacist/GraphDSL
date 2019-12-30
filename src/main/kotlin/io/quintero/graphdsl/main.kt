package io.quintero.graphdsl

import io.quintero.graphdsl.codegen.builder.KotlinCodeBuilder
import io.quintero.graphdsl.schema.Schema
import io.quintero.graphdsl.schema.fetch

fun main(args: Array<String>) {
    val url = args.firstOrNull() ?: "https://countries.trevorblades.com/"
    val schema = Schema.fetch(url)
    print(KotlinCodeBuilder().apply { +schema }.build())
}