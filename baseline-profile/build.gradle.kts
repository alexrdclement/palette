plugins {
    id(libs.plugins.alexrdclement.android.baselineprofile.generator.get().pluginId)
}

android {
    namespace = "com.alexrdclement.palette.baselineprofile"

    targetProjectPath = ":androidApp"
}

firebaseTestLab {
    managedDevices {
        create(baselineProfileGenerator.deviceName) {
            device = baselineProfileGenerator.deviceType
            apiLevel = baselineProfileGenerator.apiLevel
        }
    }
    val serviceAccountJson = System.getenv("FIREBASE_TEST_LAB_SERVICE_ACCOUNT")
    if (serviceAccountJson != null) {
        serviceAccountCredentials.set(file(serviceAccountJson))
    }
}

dependencies {
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.espresso.core)
    implementation(libs.androidx.uiautomator)
    implementation(libs.androidx.benchmark.macro.junit4)

    implementation(projects.uiautomatorFixtures)
}

androidComponents {
    onVariants { v ->
        v.instrumentationRunnerArguments.put(
            "targetAppId",
            v.testedApks.map { v.artifacts.getBuiltArtifactsLoader().load(it)?.applicationId }
        )
    }
}
