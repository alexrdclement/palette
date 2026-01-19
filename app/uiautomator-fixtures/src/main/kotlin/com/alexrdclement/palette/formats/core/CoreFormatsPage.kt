package com.alexrdclement.palette.formats.core

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class CoreFormatsPage(
    private val device: UiDevice,
) {
    fun navigateToNumber() {
        device.waitAndFindObject(By.text("Number")).click()
    }

    fun navigateToText() {
        device.waitAndFindObject(By.text("Text")).click()
    }
}
