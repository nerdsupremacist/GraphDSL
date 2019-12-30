package io.quintero.graphdsl.schema

data class SchemaResponse(val data: Data) {
    data class Data(val __schema: Schema)
}