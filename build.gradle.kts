plugins {
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
    id("com.gtnewhorizons.retrofuturagradle") version "1.3.24"
}

group = "com.slava_110.customizedwither"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

minecraft {
    javaToolchain.set(java.toolchain)

    mcVersion.set("1.12.2")

    mcpMappingChannel.set("stable")
    mcpMappingVersion.set("39")

    username.set("Developer")

    extraRunJvmArguments.addAll(
        "-ea:${project.group}"
    )

    groupsToExcludeFromAutoReobfMapping.addAll("com.diffplug", "com.diffplug.durian", "net.industrial-craft")
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
        name = "CurseMaven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }
}

dependencies {

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