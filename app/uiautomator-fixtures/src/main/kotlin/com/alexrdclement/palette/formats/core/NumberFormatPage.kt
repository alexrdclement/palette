package com.alexrdclement.palette.formats.core

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.alexrdclement.palette.waitAndFindObject

class NumberFormatPage(
    private val device: UiDevice,
) {
    fun assertIsDisplayed() {
        device.waitAndFindObject(By.text("Number"))
    }

    val text: UiObject2
        get() = device.waitAndFindObject(By.text(""))
}
