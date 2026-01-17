package com.alexrdclement.palette.formats.money

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.alexrdclement.palette.waitAndFindObject

class MoneyFormatPage(
    private val device: UiDevice,
) {
    val demo: UiObject2
        get() = device.waitAndFindObject(By.descContains(""))
}
