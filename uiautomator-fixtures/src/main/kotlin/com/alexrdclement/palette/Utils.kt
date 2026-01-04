package com.alexrdclement.palette

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

/**
 * Waits until an object with [selector] if visible on screen and returns the object.
 * If the element is not available in [timeout], throws [AssertionError]
 */
fun UiDevice.waitAndFindObject(
    selector: BySelector,
    timeout: Long = 1000,
): UiObject2 {
    waitForIdle()

    if (!wait(Until.hasObject(selector), timeout)) {
        throw AssertionError("Element not found on screen in ${timeout}ms (selector=$selector)")
    }

    return findObject(selector)
}

/**
 * Waits for window update to complete (useful after navigation actions)
 */
fun UiDevice.waitForWindowUpdate(timeout: Long = 1000) {
    waitForWindowUpdate(null, timeout)
}

/**
 * Clicks an object and waits for the window to update
 */
fun UiObject2.clickAndWaitForWindowUpdate(device: UiDevice, timeout: Long = 1000) {
    click()
    device.waitForWindowUpdate(timeout)
}
