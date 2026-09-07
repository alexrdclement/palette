package com.alexrdclement.palette

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.StaleObjectException
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

/**
 * Resource id shared by every catalog item, surfaced from Compose via `testTagsAsResourceId`.
 * Keep in sync with `CatalogItemTestTag` in the `:components` module.
 */
const val catalogItemResId = "catalogItem"

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
 * Finds an object with [selector] and clicks it, retrying if the node becomes stale mid screen
 * transition (a common cause of [StaleObjectException] when the same text/id exists on the
 * outgoing and incoming screens).
 */
fun UiDevice.waitAndClickObject(
    selector: BySelector,
    timeout: Long = 1000,
    maxAttempts: Int = 3,
) {
    repeat(maxAttempts) { attempt ->
        try {
            waitAndFindObject(selector, timeout).click()
            return
        } catch (e: StaleObjectException) {
            if (attempt == maxAttempts - 1) throw e
            waitForIdle()
        }
    }
}

/**
 * Recursively visits every catalog item reachable from the current catalog screen: clicks each
 * item, descends into any nested catalog, then navigates back. Leaf (non-catalog) destinations are
 * opened and immediately backed out of. New catalog items are covered automatically, so callers do
 * not need to enumerate them by hand.
 */
fun UiDevice.navigateCatalogItems() {
    for (title in settledCatalogItemTitles()) {
        waitAndClickObject(By.res(catalogItemResId).text(title))
        // Descend into nested catalogs; a leaf destination yields no items and is a no-op.
        navigateCatalogItems()
        pressBack()
        waitForIdle()
    }
}

/**
 * Titles of the catalog items on the current screen, read once the screen has stopped changing so
 * that items animating off the previous screen are not mistaken for the current one.
 */
private fun UiDevice.settledCatalogItemTitles(): List<String> {
    waitForIdle()
    var last = catalogItemTitles()
    repeat(maxSettleReads) {
        waitForIdle()
        val next = catalogItemTitles()
        if (next == last) return next
        last = next
    }
    return last
}

private fun UiDevice.catalogItemTitles(): List<String> =
    try {
        findObjects(By.res(catalogItemResId)).mapNotNull { it.text }
    } catch (e: StaleObjectException) {
        emptyList()
    }

private const val maxSettleReads = 10
