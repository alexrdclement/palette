import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    id(libs.plugins.alexrdclement.kotlin.multiplatform.library.get().pluginId)
    id(libs.plugins.alexrdclement.compose.multiplatform.get().pluginId)
    id(libs.plugins.alexrdclement.maven.publish.get().pluginId)
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    compilerOptions {
        optIn.add("androidx.compose.foundation.style.ExperimentalFoundationStyleApi")
    }
}

kotlin {
    libraryTargets(
        androidNamespace = "com.alexrdclement.palette.theme",
        iosFrameworkBaseName = "Theme",
    )

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.formats)
                implementation(projects.modifiers)
            }
        }
    }
}
