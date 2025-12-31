package com.alexrdclement.palette.components

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class ComponentsPage(
    private val device: UiDevice,
) {
    fun navigateToCoreComponents() {
        device.waitAndFindObject(By.text("Core")).click()
    }

    fun navigateToMediaComponents() {
        device.waitAndFindObject(By.text("Media")).click()
    }
}
