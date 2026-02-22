package com.alexrdclement.palette

import android.content.Intent
import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.components.ComponentsPage
import com.alexrdclement.palette.components.auth.AuthComponentsPage
import com.alexrdclement.palette.components.color.ColorComponentsPage
import com.alexrdclement.palette.components.core.ButtonPage
import com.alexrdclement.palette.components.core.CoreComponentsPage
import com.alexrdclement.palette.components.geometry.GeometryComponentsPage
import com.alexrdclement.palette.components.media.MediaComponentsPage
import com.alexrdclement.palette.components.media.MediaControlSheetPage
import com.alexrdclement.palette.components.money.MoneyComponentsPage
import com.alexrdclement.palette.formats.FormatsPage
import com.alexrdclement.palette.formats.core.CoreFormatsPage
import com.alexrdclement.palette.formats.core.NumberFormatPage
import com.alexrdclement.palette.formats.datetime.DateFormatPage
import com.alexrdclement.palette.formats.datetime.DateTimeFormatsPage
import com.alexrdclement.palette.formats.money.MoneyFormatPage
import com.alexrdclement.palette.formats.money.MoneyFormatsPage
import com.alexrdclement.palette.modifiers.ColorInvertPage
import com.alexrdclement.palette.modifiers.ModifiersPage
import com.alexrdclement.palette.theme.ThemePage
import com.alexrdclement.palette.theme.format.ThemeFormatsPage
import com.alexrdclement.palette.theme.format.datetime.ThemeDateTimeFormatsPage
import com.alexrdclement.palette.theme.interaction.InteractionPage
import com.alexrdclement.palette.theme.styles.StylesPage
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DeeplinkTest {

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun noIntentUri() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.startActivity(
            context.packageManager.getLaunchIntentForPackage(context.packageName)!!
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
        MainCatalogPage(device).assertIsDisplayed()
    }

    @Test
    fun emptyDeeplinkPath() {
        startDeeplink("")
        MainCatalogPage(device).assertIsDisplayed()
    }

    // Main

    @Test
    fun mainGraph() {
        startDeeplink("main")
        MainCatalogPage(device).assertIsDisplayed()
    }

    @Test
    fun mainCatalog() {
        startDeeplink("main/catalog")
        MainCatalogPage(device).assertIsDisplayed()
    }

    // Components

    @Test
    fun componentsCatalog() {
        startDeeplink("components/catalog")
        ComponentsPage(device).assertIsDisplayed()
    }

    @Test
    fun coreComponentsCatalog() {
        startDeeplink("components/core/catalog")
        CoreComponentsPage(device).assertIsDisplayed()
    }

    @Test
    fun coreButton() {
        startDeeplink("components/core/button")
        ButtonPage(device).assertIsDisplayed()
    }

    @Test
    fun authComponentsCatalog() {
        startDeeplink("components/auth/catalog")
        AuthComponentsPage(device).assertIsDisplayed()
    }

    @Test
    fun authButton() {
        startDeeplink("components/auth/button")
        DemoPage(device, title = "Button").assertIsDisplayed()
    }

    @Test
    fun colorComponentsCatalog() {
        startDeeplink("components/color/catalog")
        ColorComponentsPage(device).assertIsDisplayed()
    }

    @Test
    fun colorPicker() {
        startDeeplink("components/color/color-picker")
        DemoPage(device, title = "ColorPicker").assertIsDisplayed()
    }

    @Test
    fun moneyComponentsCatalog() {
        startDeeplink("components/money/catalog")
        MoneyComponentsPage(device).assertIsDisplayed()
    }

    @Test
    fun currencyAmountField() {
        startDeeplink("components/money/currency-amount-field")
        DemoPage(device, title = "CurrencyAmountField").assertIsDisplayed()
    }

    @Test
    fun geometryComponentsCatalog() {
        startDeeplink("components/geometry/catalog")
        GeometryComponentsPage(device).assertIsDisplayed()
    }

    @Test
    fun curveStitch() {
        startDeeplink("components/geometry/curve-stitch")
        DemoPage(device, title = "CurveStitch").assertIsDisplayed()
    }

    @Test
    fun mediaComponentsCatalog() {
        startDeeplink("components/media/catalog")
        MediaComponentsPage(device).assertIsDisplayed()
    }

    @Test
    fun mediaControlSheet() {
        startDeeplink("components/media/media-control-sheet")
        MediaControlSheetPage(device).assertIsDisplayed()
    }

    // Modifiers

    @Test
    fun modifiersCatalog() {
        startDeeplink("modifiers/catalog")
        ModifiersPage(device).assertIsDisplayed()
    }

    @Test
    fun colorInvertModifier() {
        startDeeplink("modifiers/color-invert")
        ColorInvertPage(device).assertIsDisplayed()
    }

    // Formats

    @Test
    fun formatsCatalog() {
        startDeeplink("formats/catalog")
        FormatsPage(device).assertIsDisplayed()
    }

    @Test
    fun coreFormatsCatalog() {
        startDeeplink("formats/core/catalog")
        CoreFormatsPage(device).assertIsDisplayed()
    }

    @Test
    fun coreFormatNumber() {
        startDeeplink("formats/core/number")
        NumberFormatPage(device).assertIsDisplayed()
    }

    @Test
    fun dateTimeFormatsCatalog() {
        startDeeplink("formats/datetime/catalog")
        DateTimeFormatsPage(device).assertIsDisplayed()
    }

    @Test
    fun dateTimeFormatDate() {
        startDeeplink("formats/datetime/date")
        DateFormatPage(device).assertIsDisplayed()
    }

    @Test
    fun moneyFormatsCatalog() {
        startDeeplink("formats/money/catalog")
        MoneyFormatsPage(device).assertIsDisplayed()
    }

    @Test
    fun moneyFormat() {
        startDeeplink("formats/money/money-format")
        MoneyFormatPage(device).assertIsDisplayed()
    }

    // Theme

    @Test
    fun themeCatalog() {
        startDeeplink("theme/catalog")
        ThemePage(device).assertIsDisplayed()
    }

    @Test
    fun themeStylesCatalog() {
        startDeeplink("theme/styles/catalog")
        StylesPage(device).assertIsDisplayed()
    }

    @Test
    fun themeInteractionCatalog() {
        startDeeplink("theme/interaction/catalog")
        InteractionPage(device).assertIsDisplayed()
    }

    @Test
    fun themeFormatsCatalog() {
        startDeeplink("theme/formats/catalog")
        ThemeFormatsPage(device).assertIsDisplayed()
    }

    @Test
    fun themeFormatsDateTimeCatalog() {
        startDeeplink("theme/formats/datetime/catalog")
        ThemeDateTimeFormatsPage(device).assertIsDisplayed()
    }

    @Test
    fun themeFormatsDateTimeDate() {
        startDeeplink("theme/formats/datetime/date")
        DemoPage(device, title = "Date").assertIsDisplayed()
    }

    private fun startDeeplink(path: String) {
        InstrumentationRegistry.getInstrumentation().targetContext.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("palette://app/$path"))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    private fun startIntent(intent: Intent) {
        InstrumentationRegistry.getInstrumentation().targetContext.startActivity(
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
