plugins {
    id(libs.plugins.alexrdclement.android.library.asProvider().get().pluginId)
    id(libs.plugins.alexrdclement.android.library.compose.get().pluginId)
    id(libs.plugins.alexrdclement.android.compose.test.get().pluginId)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.alexrdclement.palette.components"
}

dependencies {
    implementation(projects.components)
    implementation(projects.theme.components)
    testImplementation(libs.junit)
    testImplementation(libs.test.parameter.injector)
    testImplementation(projects.components)
    testImplementation(projects.theme.components)
    testImplementation(projects.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
