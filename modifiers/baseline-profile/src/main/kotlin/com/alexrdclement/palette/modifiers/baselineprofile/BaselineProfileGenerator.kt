package com.alexrdclement.palette.modifiers.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.alexrdclement.palette.MainCatalogPage
import com.alexrdclement.palette.appPackageName
import com.alexrdclement.palette.modifierPackageName
import com.alexrdclement.palette.modifiers.ColorInvertPage
import com.alexrdclement.palette.modifiers.ColorSplitPage
import com.alexrdclement.palette.modifiers.FadePage
import com.alexrdclement.palette.modifiers.ModifiersPage
import com.alexrdclement.palette.modifiers.NoisePage
import com.alexrdclement.palette.modifiers.PixelatePage
import com.alexrdclement.palette.modifiers.WarpPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generateModifiersProfile() {
        rule.collect(
            packageName = appPackageName,
            filterPredicate = { packageFilterPredicate(modifierPackageName, it) },
        ) {
            pressHome()
            startActivityAndWait()

            MainCatalogPage(device).navigateToModifiers()
            val modifiersPage = ModifiersPage(device)

            modifiersPage.navigateToColorInvert()
            ColorInvertPage(device).adjustColorInvert()
            device.pressBack()

            modifiersPage.navigateToColorSplit()
            ColorSplitPage(device).adjustColorSplit()
            device.pressBack()

            modifiersPage.navigateToFade()
            FadePage(device).adjustFade()
            device.pressBack()

            modifiersPage.navigateToNoise()
            NoisePage(device).adjustNoise()
            device.pressBack()

            modifiersPage.navigateToPixelate()
            PixelatePage(device).adjustPixelate()
            device.pressBack()

            modifiersPage.navigateToWarp()
            WarpPage(device).adjustWarp()
            device.pressBack()
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
