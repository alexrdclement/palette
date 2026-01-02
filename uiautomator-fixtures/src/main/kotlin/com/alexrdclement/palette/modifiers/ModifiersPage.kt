package com.alexrdclement.palette.modifiers

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class ModifiersPage(
    private val device: UiDevice,
) {
    fun navigateToColorInvert() {
        navigateToModifier("ColorInvert")
    }

    fun navigateToColorSplit() {
        navigateToModifier("ColorSplit")
    }

    fun navigateToNoise() {
        navigateToModifier("Noise")
    }

    fun navigateToPixelate() {
        navigateToModifier("Pixelate")
    }

    fun navigateToWarp() {
        navigateToModifier("Warp")
    }

    private fun navigateToModifier(modifierName: String) {
        device.waitAndFindObject(By.text(modifierName)).click()
    }
}
