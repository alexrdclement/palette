import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id(libs.plugins.alexrdclement.desktop.application.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    alias(libs.plugins.compose.hotreload)
}

kotlin {
    desktopAppTarget(
        mainClass = "com.alexrdclement.palette.MainKt",
    )
    sourceSets {
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(projects.app)
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
