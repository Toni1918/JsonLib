plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "pt.jsonlib"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin stdlib
    implementation(kotlin("stdlib"))

    // Testes com JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    // OkHttp para testes HTTP (opcional)
    testImplementation("com.squareup.okhttp3:okhttp:4.12.0")
    testImplementation("com.squareup.okio:okio:3.9.0")
}

kotlin {
    sourceSets {
        getByName("main").kotlin.srcDir("src/main/kotlin")
        getByName("test").kotlin.srcDir("src/test/kotlin")
    }
}

tasks.test {
    useJUnitPlatform()
}

application {

    mainClass.set("server.MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "server.MainKt"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}
