package io.quintero.graphdsl.schema

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.DataOutput
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

fun Schema.Companion.fetch(url: String): Schema {
    val connection = URL(url).openConnection() as HttpURLConnection
    connection.requestMethod = "POST"
    connection.doOutput = true
    connection.setRequestProperty("Content-type", "application/json")

    val body = DataOutputStream(connection.outputStream)
    val mapper = jacksonObjectMapper()

    mapper.writeValue(body as DataOutput, Query(query))
    return mapper.readValue<SchemaResponse>(connection.inputStream).data.__schema
}

// I found this query by checking what GraphiQL runs in the background ;)
private const val query = """
    query IntrospectionQuery {
      __schema {
        queryType {
          name
        }
        types {
          ...FullType
        }
      }
    }
    
    fragment FullType on __Type {
      kind
      name
      fields(includeDeprecated: true) {
        name
        args {
          ...InputValue
        }
        type {
          ...TypeRef
        }
      }
      inputFields {
        ...InputValue
      }
      interfaces {
        ...TypeRef
      }
      enumValues(includeDeprecated: true) {
        name
      }
      possibleTypes {
        ...TypeRef
      }
    }
    
    fragment InputValue on __InputValue {
      name
      type {
        ...TypeRef
      }
      defaultValue
    }
    
    fragment TypeRef on __Type {
      kind
      name
      ofType {
        kind
        name
        ofType {
          kind
          name
          ofType {
            kind
            name
            ofType {
              kind
              name
              ofType {
                kind
                name
                ofType {
                  kind
                  name
                  ofType {
                    kind
                    name
                  }
                }
              }
            }
          }
        }
      }
    }
"""

private data class Query(val query: String)