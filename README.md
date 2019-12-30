# GraphDSL
Script that given a GraphQL Server will create a Kotlin DSL for writing queries

## Usage

This script takes a url to a graphql endpoint as an argument, and will generate a dsl for building queries, and print it on stdout.

For example:

Run the script:

```
[path-executable] https://swapi-graphql.netlify.com/.netlify/functions/index
```

With the generated code you can create queries in the following format:

```kotlin
fun main() {
    val personFragment = Person("MyCustomPerson") {
        eyeColor()
        gender()
        birthYear()
    }

    val query = GraphQL {
        allPeople(first = 10) {
            edges {
                node {
                    +personFragment

                    name()
                    homeworld {
                        name()
                    }
                }
            }
        }
    }
}
```

### Limitations

This is only a proof of concept. The generated code actually won't generate any queries yet. It only provides the API. The code will crash due to inserted TODOs.
It will write the query code in the future

This script also doesn't handle interfaces or union types yet.
This script does not handle default values for the fields other than `null`
