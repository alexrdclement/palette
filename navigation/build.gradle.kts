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
        val nonWebMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.androidx.navigationevent)
            }
        }

        androidMain {
            dependsOn(nonWebMain)
        }
        jvmMain {
            dependsOn(nonWebMain)
        }
        iosMain {
            dependsOn(nonWebMain)
        }
        wasmJsMain {
            dependencies {
                implementation(libs.navigation3.browser)
            }
        }
    }
}
