plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.kotlin.serialization.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.navigation",
        iosFrameworkBaseName = "Navigation",
    )

    sourceSets {
        commonMain {
            dependencies {
                api(libs.navigation3.ui)
                api(libs.navigation3.runtime)
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}
