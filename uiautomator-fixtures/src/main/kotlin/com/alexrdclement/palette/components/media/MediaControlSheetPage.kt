package com.alexrdclement.palette.components.media

import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.alexrdclement.palette.waitAndFindObject

class MediaControlSheetPage(
    private val device: UiDevice,
) {
    val mediaControlBar: UiObject2
        get() = device.waitAndFindObject(By.descContains("Media control bar"))

    fun expandSheet() {
        mediaControlBar.click()
        device.waitForIdle()
    }
}
