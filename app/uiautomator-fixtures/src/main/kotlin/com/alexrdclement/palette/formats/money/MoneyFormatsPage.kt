package com.alexrdclement.palette.formats.money

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.alexrdclement.palette.waitAndFindObject

class MoneyFormatsPage(
    private val device: UiDevice,
) {
    fun assertIsDisplayed() {
        device.waitAndFindObject(By.text("Money"))
    }

    fun navigateToMoney() {
        device.waitAndFindObject(By.text("Money")).click()
    }
}
