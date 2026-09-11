package com.alexrdclement.palette.theme.component.core

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.toFoundationStyle

/**
 * PROTOTYPE / SPIKE — palette's theme button styles exposed as foundation
 * [Style] values, so they can be passed as `style` arguments (e.g. to
 * StyledButton) the same way [ButtonStyles] is passed today.
 *
 * This reuses the existing token pipeline: [ButtonStyleToken.resolve] resolves a
 * token set to a concrete component `ButtonStyle`, and `toFoundationStyle()`
 * expresses that data as a foundation [Style]. Resolution happens at
 * composition time (as it does today), so theme values are captured into the
 * style; interaction/enabled state is handled by the styleable modifier.
 */
@OptIn(ExperimentalFoundationStyleApi::class)
object ButtonFoundationStyles {
    val primary: Style @Composable get() = ButtonStyleToken.Primary.resolve().toFoundationStyle()
    val secondary: Style @Composable get() = ButtonStyleToken.Secondary.resolve().toFoundationStyle()
    val tertiary: Style @Composable get() = ButtonStyleToken.Tertiary.resolve().toFoundationStyle()

    @Composable
    operator fun get(token: ButtonStyleToken): Style = token.resolve().toFoundationStyle()
}
