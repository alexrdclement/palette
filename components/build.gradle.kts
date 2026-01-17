plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.android.baselineprofile.consumer.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.components",
        iosFrameworkBaseName = "Components",
    )

    sourceSets {
        commonMain {
            dependencies {
                api(libs.coil.compose)
                api(libs.coil.network.ktor3)
                api(libs.kotlinx.collections.immutable)
                api(libs.kotlinx.datetime)
                api(libs.trace)

                api(projects.theme)

                implementation(libs.compose.material.icons.extended)
            }
        }
        androidMain {
            dependencies {
                api(libs.ktor.client.android)
                api(libs.ktor.client.okhttp)
            }
        }
        appleMain {
            dependencies {
                api(libs.ktor.client.darwin)
            }
        }
        jvmMain {
            dependencies {
                api(libs.ktor.client.java)
            }
        }
    }
}

dependencies {
    baselineProfile(projects.components.baselineProfile)
}
