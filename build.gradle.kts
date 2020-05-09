import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    id("kotlinx-serialization") version "1.3.30"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { setUrl("https://kotlin.bintray.com/kotlinx") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.0")

    implementation("com.github.librepdf:openpdf:1.3.16")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
