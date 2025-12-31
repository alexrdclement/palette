package com.alexrdclement.palette.modifiers.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.alexrdclement.palette.MainCatalogPage
import com.alexrdclement.palette.appPackageName
import com.alexrdclement.palette.modifiers.ShadersPage
import com.alexrdclement.palette.shadersPackageName
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    // Run with `gradle shaders:generateBaselineProfile`
    @Test
    fun generateShadersProfile() {
        rule.collect(
            packageName = appPackageName,
            filterPredicate = { packageFilterPredicate(shadersPackageName, it) },
        ) {
            pressHome()
            startActivityAndWait()

            MainCatalogPage(device).navigateToShaders()
            val shadersPage = ShadersPage(device)

            shadersPage.selectColorInvert()
            shadersPage.adjustColorInvert()

            shadersPage.selectColorSplit()
            shadersPage.adjustColorSplit()

            shadersPage.selectNoise()
            shadersPage.adjustNoise()

            shadersPage.selectPixelate()
            shadersPage.adjustPixelate()

            shadersPage.selectGridLineSubject()

            shadersPage.selectWarp()
            shadersPage.adjustWarp()
        }
    }

    private fun packageFilterPredicate(packageName: String, rule: String): Boolean {
        // Only capture rules in the library's package, excluding test app code
        // Rules are prefixed by tag characters, followed by JVM method signature,
        // e.g. `HSPLcom/mylibrary/LibraryClass;-><init>()V`, where `L`
        // signifies the start of the package/class, and '/' is divider instead of '.'
        return rule.contains("^.*L${packageName.replace(".", "/")}".toRegex())
    }
}
