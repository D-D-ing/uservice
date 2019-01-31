import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.20"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.20"
    id("org.springframework.boot") version "2.1.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"

    id("org.jlleitschuh.gradle.ktlint") version "7.0.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "7.0.0"
}

group = "dding.uservice"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter-web:2.1.2.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.1.2.RELEASE")

    implementation("org.hibernate:hibernate-core:5.2.9.Final")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("com.graphql-java:graphql-java:8.0")
    implementation("com.graphql-java:graphiql-spring-boot-starter:4.0.0")
    implementation("com.graphql-java:graphql-java-tools:5.0.0")
    implementation("com.graphql-java:graphql-spring-boot-starter:4.0.0")
    implementation("com.graphql-java:graphql-java-servlet:5.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.1.2.RELEASE")
}

val compileKotlin by tasks.getting(KotlinCompile::class) {
    // Customise the “compileKotlin” task.
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
    doLast { println("Finished compiling Kotlin source code") }
}
val compileTestKotlin by tasks.getting(KotlinCompile::class) {
    // Customise the “compileTestKotlin” task.
    kotlinOptions.jvmTarget = "1.8"
    doLast { println("Finished compiling Kotlin source code for testing") }
}
