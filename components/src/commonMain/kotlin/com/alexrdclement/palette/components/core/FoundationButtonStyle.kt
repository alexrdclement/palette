package com.alexrdclement.palette.components.core

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.StyleScope
import androidx.compose.foundation.style.StyleState
import androidx.compose.foundation.style.contentPadding
import androidx.compose.foundation.style.disabled
import androidx.compose.foundation.style.pressed
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.scale
import androidx.compose.foundation.style.styleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * PROTOTYPE / SPIKE — not wired into any shipping component yet.
 *
 * Ports palette's parameter-based [ButtonStyle] onto the experimental Compose
 * foundation Style API (`androidx.compose.foundation.style`), which became
 * available in Compose Multiplatform 1.12.0.
 *
 * The point of the spike is to show that palette's existing style data can be
 * expressed as a first-class foundation [Style] and applied through the new
 * `styleable` modifier, including state-based styling (disabled / pressed) that
 * previously had to be threaded through parameters and `graphicsLayer`.
 */
@OptIn(ExperimentalFoundationStyleApi::class)
fun ButtonStyle.toFoundationStyle(): Style = Style {
    // Container + shape, mapped straight from the palette data class.
    background(containerColor)
    shape(shape.toComposeShape())

    // Border, if any.
    borderStyle?.let { border ->
        borderWidth(border.width)
        borderColor(border.color)
    }

    // StyleScope is a Density + CompositionLocalAccessorScope, so PaddingValues
    // can be applied directly.
    contentPadding(contentPadding)

    // State-based styling that the parameter-based API could not express
    // declaratively: dim the container when disabled, and shrink slightly while
    // pressed. Foundation animates the transitions between these automatically.
    disabled {
        background(containerColor.copy(alpha = disabledContainerAlpha))
    }
    pressed {
        scale(0.98f)
    }
}

/**
 * Applies [style] to this modifier via the foundation `styleable` modifier,
 * driving state (pressed/hovered/focused/enabled) from [interactionSource] and
 * [enabled].
 */
@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun Modifier.paletteButtonStyleable(
    style: ButtonStyle,
    enabled: Boolean = true,
    interactionSource: InteractionSource = remember { MutableInteractionSource() },
): Modifier {
    val styleState: StyleState = rememberUpdatedStyleState(interactionSource) {
        it.isEnabled = enabled
    }
    return this.styleable(styleState, style.toFoundationStyle())
}
