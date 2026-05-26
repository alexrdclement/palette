package com.alexrdclement.palette.theme.styles

import androidx.compose.foundation.style.Style
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.LocalPaletteColorScheme
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.style.background
import com.alexrdclement.palette.theme.style.border
import com.alexrdclement.palette.theme.toColor

enum class ButtonStyleToken {
    Primary,
    Secondary,
    Tertiary,
}

data class ButtonStyle(
    val token: ButtonStyleToken,
    val contentColor: ColorToken,
    val containerColor: ColorToken,
    val shape: ShapeToken,
    val borderStyle: BorderStyleToken?,
)

// Converts a ButtonStyle to a Foundation Style for use with Modifier.styleable.
// Handles background and border rendering including disabled-state dimming.
// ContentColor is intentionally excluded — it must be passed as an explicit parameter
// to Surface/Button because Foundation's StyleScope.contentColor() propagates via
// modifier node traversal, not CompositionLocals, and Palette's Text reads
// LocalContentColor.current rather than the Foundation node.
fun ButtonStyle.toRenderStyle(): Style = Style {
    background(containerColor)
    borderStyle?.let { border(it) }
    disabled {
        val scheme = LocalPaletteColorScheme.currentValue
        background(containerColor.toColor(scheme).copy(alpha = scheme.disabledContainerAlpha))
    }
}

fun ButtonStyleToken.toStyle(buttonStyles: ButtonStyleScheme): ButtonStyle {
    return when (this) {
        ButtonStyleToken.Primary -> buttonStyles.primary
        ButtonStyleToken.Secondary -> buttonStyles.secondary
        ButtonStyleToken.Tertiary -> buttonStyles.tertiary
    }
}

@Composable
fun ButtonStyleToken.toStyle(): ButtonStyle {
    return toStyle(PaletteTheme.styles.button)
}
