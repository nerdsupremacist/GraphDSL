package io.quintero.graphdsl.schema

import io.quintero.graphdsl.codegen.builder.*

data class Type(
        val name: String,
        val kind: Kind,
        val fields: Iterable<Field>?,
        val possibleTypes: Iterable<TypeReference>?,
        val interfaces: Iterable<TypeReference>?,
        val enumValues: Iterable<TypeReference>?,
        val inputFields: Iterable<InputField>?
) : CodeConvertible<KotlinCodeBuilder> {

    override fun KotlinCodeBuilder.invoke() {

        val builder = object : CodeConvertible<KotlinCodeBuilder> {

            override fun KotlinCodeBuilder.invoke() {
                val fields = fields ?: return
                if (fields.count() < 1) return

                kotlinClass {
                    name = "Builder"

                    superClass {
                        idenfitier {
                            name = "GraphQLBuilder"
                            addGeneric(this@Type.name)
                        }
                    }

                    body {
                        fields.forEach { field ->
                            function { +field }
                        }
                    }
                }
            }

        }

        when (kind) {

            Kind.NON_NULL,
            Kind.LIST,
            Kind.SCALAR -> return

            Kind.INTERFACE,
            Kind.UNION,
            Kind.ENUM,
            Kind.OBJECT -> enum {
                name = this@Type.name
                enumValues?.forEach { case ->
                    case.name?.let(::addCase)
                }
                body {
                    +builder
                }
            }

            Kind.INPUT_OBJECT -> kotlinClass {
                isDataClass = true
                name = this@Type.name
                inputFields?.forEach { inputField ->
                    argument {
                        +inputField
                    }
                }
                body {
                    +builder
                }
            }
        }
    }

}