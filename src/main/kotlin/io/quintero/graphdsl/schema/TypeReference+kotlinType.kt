package io.quintero.graphdsl.schema

val TypeReference.kotlinType: String
    get() {
        ofType?.let { ofType ->
            return when (kind) {
                Kind.NON_NULL ->
                    ofType.kotlinType.removeSuffix("?")

                Kind.LIST ->
                    "Iterable<${ofType.kotlinType}>?"

                else -> ofType.kotlinType
            }
        }

        return when (kind) {
            Kind.SCALAR -> when (name) {
                "Boolean" -> "Boolean?"
                "Int" -> "Int?"
                "Float" -> "Float?"
                else -> "String?"
            }
            else -> name?.let { "$it?" } ?: "Any"
        }
    }

val TypeReference.underlyingType: TypeReference
    get() {
        return ofType?.underlyingType ?: this
    }


val TypeReference.nonNull: TypeReference
    get() {
        return when (kind) {
            Kind.NON_NULL -> this
            else -> TypeReference(null, Kind.NON_NULL, this)
        }
    }

