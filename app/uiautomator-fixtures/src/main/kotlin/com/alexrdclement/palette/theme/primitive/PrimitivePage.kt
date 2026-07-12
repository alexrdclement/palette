package com.alexrdclement.palette.theme.primitive

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class PrimitivePage(
    private val device: UiDevice,
) {
    fun assertIsDisplayed() {
        device.waitAndFindObject(By.text("Typography"))
    }
}
