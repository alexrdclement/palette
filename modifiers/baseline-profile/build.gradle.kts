plugins {
    id(libs.plugins.alexrdclement.android.baselineprofile.generator.get().pluginId)
}

android {
    namespace = "com.alexrdclement.palette.modifiers.baselineprofile"

    targetProjectPath = ":app:androidApp"
}

baselineProfileGenerator {
    copyToLibrary = ":modifiers"
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
    testOptions {
        results.cloudStorageBucket = "firebase-test-lab-palette"
    }
}

dependencies {
    implementation(projects.app.uiautomatorFixtures)
}
