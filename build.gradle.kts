plugins {
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
    id("com.gtnewhorizons.retrofuturagradle") version "1.4.0"
}

group = "com.slava_110.pixietable"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

minecraft {
    mcVersion.set("1.12.2")

    mcpMappingChannel.set("stable")
    mcpMappingVersion.set("39")

    username.set("Developer")
}

repositories {
    maven {
        name = "CleanroomMC Maven"
        url = uri("https://maven.cleanroommc.com")
    }
    maven {
        name = "SpongePowered Maven"
        url = uri("https://repo.spongepowered.org/maven")
    }
    maven {
        name = "BlameJared"
        url = uri("https://maven.blamejared.com")
    }
    maven {
        name = "CurseMaven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
}

dependencies {
    implementation(rfg.deobf("CraftTweaker2:CraftTweaker2-MC1120-Main:1.12-4.1.20.692"))
    implementation("mezz.jei:jei_1.12.2:4.16.1.1012")
}

tasks.named<JavaExec>("runObfClient") {
    workingDir("run-client-obf")
}

tasks.named<JavaExec>("runObfServer") {
    mainClass.set("net.minecraftforge.fml.relauncher.ServerLaunchWrapper")
    workingDir("run-server-obf")
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("mcmod.info") {
        expand(
            "modVersion" to project.version
        )
    }
}

// IDE Settings
idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
        inheritOutputDirs = true // Fix resources in IJ-Native runs
    }
}

tasks.processIdeaSettings {
    dependsOn(tasks.injectTags)
}