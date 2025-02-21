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
    certificateChainFile.set(file("chain.crt"))
    privateKeyFile.set(file("private.pem"))
    password.set("andrey")
}

tasks.publishPlugin {
    token.set("perm:a961riC....l17oW8t+Qw==")
}

tasks.patchPluginXml {
    version = "${project.version}"
    sinceBuild.set("233")
    untilBuild.set("243.*")
}
