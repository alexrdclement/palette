import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id(libs.plugins.embarrasdf.desktop.application.get().pluginId)
    id(libs.plugins.embarrasdf.compose.multiplatform.get().pluginId)
}

kotlin {
    desktopAppTarget(
        mainClass = "com.alexrdclement.palette.MainKt",
    )
    sourceSets {
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(projects.app.composeApp)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.alexrdclement.palette.MainKt"

        nativeDistributions {
            packageName = "palette"
            packageVersion = "1.0.0"
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Exe)
        }
    }
}
