package io.quintero.graphdsl.schema

import io.quintero.graphdsl.codegen.builder.CodeConvertible
import io.quintero.graphdsl.codegen.builder.FunctionBuilder

data class Field(
        val name: String,
        val args: Iterable<Argument>,
        val type: TypeReference
): CodeConvertible<FunctionBuilder> {

    override fun FunctionBuilder.invoke() {
        addAnnotation {
            name = "GraphQLDsl"
        }
        name = this@Field.name
        args.forEach { argument ->
            addArgument {
                +argument
            }
        }

        body {
            +"TODO()"
        }

        when (type.underlyingType.kind) {
            Kind.SCALAR,
            Kind.ENUM -> return

            Kind.OBJECT,
            Kind.LIST,
            Kind.NON_NULL,
            Kind.INTERFACE,
            Kind.INPUT_OBJECT,
            Kind.UNION -> Unit
        }

        addArgument {
            name = "_init"
            type {
                lambda {
                    target {
                        name = "${type.underlyingType.nonNull.kotlinType}.Builder"
                    }
                }
            }
        }
    }

}