plugins {
    id(libs.plugins.alexrdclement.web.application.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
}

kotlin {
    webAppTarget()

    sourceSets {
        wasmJsMain {
            dependencies {
                implementation(projects.catalogApp.app)
                implementation(compose.ui)
                implementation(libs.navigation.compose)
            }
        }
    }
}
