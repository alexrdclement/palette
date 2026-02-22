package com.alexrdclement.palette.formats

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class FormatsPage(
    private val device: UiDevice,
) {
    fun assertIsDisplayed() {
        device.waitAndFindObject(By.text("Core"))
    }

    fun navigateToCoreFormats() {
        device.waitAndFindObject(By.text("Core")).click()
    }

    fun navigateToDateTimeFormats() {
        device.waitAndFindObject(By.text("DateTime")).click()
    }

    fun navigateToMoneyFormats() {
        device.waitAndFindObject(By.text("Money")).click()
    }
}
