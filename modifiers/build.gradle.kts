plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.android.baselineprofile.consumer.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.modifiers",
        iosFrameworkBaseName = "Modifiers",
    )

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.compose.material3)
                implementation(libs.trace)
            }
        }

        val skikoMain by creating {
            dependsOn(commonMain.get())
        }

        iosMain {
            dependsOn(skikoMain)
        }
        jvmMain {
            dependsOn(skikoMain)
        }
        wasmJsMain {
            dependsOn(skikoMain)
        }
    }
}

dependencies {
    baselineProfile(projects.modifiers.baselineProfile)
}
