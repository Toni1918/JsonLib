# JSON Library – Kotlin

Projeto desenvolvido para a unidade curricular **Programação Avançada – 2024/2025**.

Esta biblioteca foi construída em Kotlin com o objetivo de **modelar, manipular e inferir estruturas JSON** sem recorrer a bibliotecas externas. Toda a lógica de serialização, inferência e manipulação de JSON foi implementada de raiz.

---

## Funcionalidades

### Fase 1 – Modelo JSON

- Implementação manual dos principais tipos JSON:
  - `JsonString`
  - `JsonNumber`
  - `JsonBoolean`
  - `JsonNull`
  - `JsonArray`
  - `JsonObject`
- Conversão direta para `toJsonString()`.

### Fase 2 – Inferência com Reflection

- Conversão automática de objetos Kotlin para JSON via:
  ```kotlin
  fun fromKotlinObject(obj: Any?): JsonValue
  ```
- Suporte completo para:
  - `Int`, `Double`, `Boolean`, `String`
  - `Enum`
  - `List<T>`
  - `Map<String, *>`
  - `Data class`
  - `null`
- Utiliza `kotlin.reflect.full.memberProperties` para analisar data classes dinamicamente.

### Fase 3 – API HTTP em Kotlin

- Mini-framework HTTP construído de raiz (sem Spring).
- Mapeamento automático de endpoints através de anotações personalizadas:
  - `@Mapping`, `@Param`, `@Path`
- Converte automaticamente os retornos em JSON com base na biblioteca criada.
- Suporte a múltiplos controladores.

---

## Exemplos de Endpoints

Assumindo que o servidor corre em `http://localhost:8080`:

| Endpoint                 | Descrição                                     | Exemplo de Resposta                |
|--------------------------|-----------------------------------------------|------------------------------------|
| `/api/ints`              | Retorna uma lista de inteiros                 | `[1.0, 2.0, 3.0]`                         |
| `/api/pair`              | Retorna um par de strings                     | `{"first": "um", "second": "dois"}`|
| `/api/path/a`            | Adiciona `!` a um valor de path               | `"a!"`                              |
| `/api/args?n=3&text=PA`  | Repete texto `n` vezes como valor de chave    | `{"PA": "PAPAPA"}`                 |

---

## Testes

- Testes unitários para:
  - Todos os tipos JSON
  - Serialização
  - Inferência com listas, mapas, enums e data classes
- Testes à API usando `OkHttpClient` (Gradle).
- Os testes estão organizados por ficheiro (`JsonArrayTest.kt`, `InferenceTest.kt`, etc).

Para correr os testes:

```bash
./gradlew test
```

---

## Requisitos

- Kotlin >= 1.9
- Gradle >= 8
- JDK >= 17

### build.gradle.kts

```kotlin
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("com.squareup.okhttp3:okhttp:4.12.0")
}
```

---

## Compilar e correr

```bash
./gradlew build
./gradlew run
```

---

## Elaborado por

- António Branco
- Rodrigo Fonseca
