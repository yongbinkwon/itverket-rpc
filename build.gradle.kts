import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("org.springframework.boot") version "3.1.2"
    application
}

apply(plugin = "io.spring.dependency-management")

group = "no.itverket"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("no.itverket.rpc.ApplicationKt")
}