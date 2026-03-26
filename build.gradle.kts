plugins {
    kotlin("jvm") version "2.3.10"
    `java-library`
    `maven-publish`
}

group = "org.example"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Expose coroutines API to the consumer
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AJThePro99/sample-kotlin-library")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}