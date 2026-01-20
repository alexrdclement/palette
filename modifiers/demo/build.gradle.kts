plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.modifiers.demo",
        iosFrameworkBaseName = "ModifiersDemo",
    )

    sourceSets {
        commonMain {
            dependencies {
                api(projects.components.demo)
                api(projects.modifiers)
                api(projects.theme)
            }
        }
    }
}
