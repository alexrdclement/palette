plugins {
    id(libs.plugins.embarrasdf.android.library.asProvider().get().pluginId)
}

android {
    namespace = "com.alexrdclement.palette"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(libs.androidx.uiautomator)
}
