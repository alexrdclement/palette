package com.alexrdclement.palette.theme

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class ThemePage(
    private val device: UiDevice,
) {
    fun assertIsDisplayed() {
        device.waitAndFindObject(By.text("Color"))
    }
}
