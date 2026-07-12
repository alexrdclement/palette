package com.alexrdclement.palette.theme.semantic.interaction

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class InteractionPage(
    private val device: UiDevice,
) {
    fun assertIsDisplayed() {
        device.waitAndFindObject(By.text("Indication"))
    }
}
