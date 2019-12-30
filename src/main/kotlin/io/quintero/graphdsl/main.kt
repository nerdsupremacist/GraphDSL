package io.quintero.graphdsl

import io.quintero.graphdsl.codegen.builder.KotlinCodeBuilder
import io.quintero.graphdsl.schema.Schema
import io.quintero.graphdsl.schema.fetch

private const val starWarsAPI = "https://swapi-graphql.netlify.com/.netlify/functions/index"

fun main(args: Array<String>) {
    val url = args.firstOrNull() ?: starWarsAPI
    val schema = Schema.fetch(url)
    print(KotlinCodeBuilder().apply { +schema }.build())
}