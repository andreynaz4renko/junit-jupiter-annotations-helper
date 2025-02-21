plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.2.1"
}

group = "ru.andreynaz4renko"
version = "1.0.3"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2023.3")
        bundledPlugins("com.intellij.java")
    }

    implementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
}

tasks.buildSearchableOptions {
    enabled = false
}

tasks.signPlugin {
    certificateChain.set(providers.environmentVariable("CERTIFICATE_CHAIN"))
    privateKey.set(providers.environmentVariable("PRIVATE_KEY"))
    password.set(providers.environmentVariable("PRIVATE_KEY_PASSWORD"))
}

tasks.publishPlugin {
    token.set(providers.environmentVariable("PUBLISH_TOKEN"))
}

tasks.patchPluginXml {
    version = "${project.version}"
    sinceBuild.set("233")
    untilBuild.set("243.*")
}
