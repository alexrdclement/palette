plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.components.demo",
        iosFrameworkBaseName = "ComponentsDemo",
    )

    sourceSets {
        commonMain {
            dependencies {
                api(projects.components)
                api(projects.formats.demo)
                api(projects.theme)
            }
        }
    }
}
