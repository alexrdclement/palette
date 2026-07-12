plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.theme.components",
        iosFrameworkBaseName = "ThemeComponents",
    )

    sourceSets {
        commonMain {
            dependencies {
                api(projects.components)
                api(projects.theme)
            }
        }
    }
}
