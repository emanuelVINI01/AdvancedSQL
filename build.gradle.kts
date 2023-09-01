import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "com.emanuelvini"
version = "1.0.0"

repositories {
    mavenCentral()

}

dependencies {
    testImplementation(kotlin("test"))


    implementation("com.zaxxer:HikariCP:2.3.2")
    implementation("org.xerial:sqlite-jdbc:3.30.1")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

