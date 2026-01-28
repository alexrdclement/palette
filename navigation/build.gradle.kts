plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.navigation",
        iosFrameworkBaseName = "Navigation",
    )

    sourceSets {
        androidMain {
            dependencies {
                implementation(libs.androidx.navigationevent)
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.androidx.navigationevent)
            }
        }
        iosMain {

        }
        wasmJsMain {
            dependencies {
                implementation(libs.navigation3.browser)
            }
        }
    }
}
