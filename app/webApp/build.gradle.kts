plugins {
    id(libs.plugins.embarrasdf.web.application.get().pluginId)
    id(libs.plugins.embarrasdf.compose.multiplatform.get().pluginId)
}

kotlin {
    webAppTarget()

    sourceSets {
        wasmJsMain {
            dependencies {
                implementation(projects.app.composeApp)
                implementation(projects.navigation)
                implementation(libs.navigation3.browser)
            }
        }
    }
}
