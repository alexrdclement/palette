package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.LocalPaletteColorScheme
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.style.PaletteStyle
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

// Converts a ButtonStyle to a PaletteStyle for use with Modifier.styleable.
// Encodes background, border, contentColor, and their disabled-state variants.
// contentColor() propagates to child BasicText via Foundation's TextStyleProviderNode.
// LocalContentColor is still separately provided by Button via CompositionLocalProvider
// for non-text drawing code (Canvas, Image) that can't observe the modifier-node path.
fun ButtonStyle.toRenderStyle(): PaletteStyle = PaletteStyle {
    background(containerColor)
    contentColor(contentColor)
    borderStyle?.let { border(it) }
    disabled {
        val scheme = LocalPaletteColorScheme.currentValue
        background(containerColor.toColor(scheme).copy(alpha = scheme.disabledContainerAlpha))
        contentColor(contentColor.toColor(scheme).copy(alpha = scheme.disabledContentAlpha))
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
