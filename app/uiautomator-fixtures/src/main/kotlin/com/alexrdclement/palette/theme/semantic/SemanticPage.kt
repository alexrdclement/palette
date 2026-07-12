package com.alexrdclement.palette.theme.semantic

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class SemanticPage(
    private val device: UiDevice,
) {
    fun assertIsDisplayed() {
        device.waitAndFindObject(By.text("Color"))
    }
}
