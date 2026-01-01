package com.alexrdclement.palette.modifiers.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.alexrdclement.palette.MainCatalogPage
import com.alexrdclement.palette.appPackageName
import com.alexrdclement.palette.modifiers.ModifiersPage
import com.alexrdclement.palette.modifierPackageName
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
            filterPredicate = { packageFilterPredicate(modifierPackageName, it) },
        ) {
            pressHome()
            startActivityAndWait()

            MainCatalogPage(device).navigateToModifiers()
            val modifiersPage = ModifiersPage(device)

            modifiersPage.selectColorInvert()
            modifiersPage.adjustColorInvert()

            modifiersPage.selectColorSplit()
            modifiersPage.adjustColorSplit()

            modifiersPage.selectNoise()
            modifiersPage.adjustNoise()

            modifiersPage.selectPixelate()
            modifiersPage.adjustPixelate()

            modifiersPage.selectGridLineSubject()

            modifiersPage.selectWarp()
            modifiersPage.adjustWarp()
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
