package com.alexrdclement.palette.theme.style

import androidx.compose.foundation.style.StyleScope
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.LocalPaletteColorScheme
import com.alexrdclement.palette.theme.LocalPaletteShapes
import com.alexrdclement.palette.theme.LocalPaletteStyles
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.modifiers.toStyle
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toComposeShape
import com.alexrdclement.palette.theme.toShape

// StyleScope extensions that bridge Palette's token system into the Foundation Style API.
//
// Inside Style lambdas, CompositionLocals must be read via .currentValue (not .current) because
// the lambda executes outside of Composition during the draw/layout phase.
//
// Note: StyleScope.contentColor() is intentionally omitted. Foundation propagates content color
// via modifier node tree traversal (TextStyleProviderNode), which bypasses Palette's own
// LocalContentColor. Palette's Text reads LocalContentColor.current directly, so calling
// contentColor() in a Style has no effect on Palette text. Content color must be passed as an
// explicit parameter to Surface, Button, and other components.

fun StyleScope.background(token: ColorToken) =
    background(token.toColor(LocalPaletteColorScheme.currentValue))

fun StyleScope.shape(token: ShapeToken) =
    shape(token.toShape(LocalPaletteShapes.currentValue).toComposeShape())

fun StyleScope.border(token: BorderStyleToken) {
    val borderStyle = token.toStyle(LocalPaletteStyles.currentValue.border)
    val color = borderStyle.color.toColor(LocalPaletteColorScheme.currentValue)
    val shape = borderStyle.shape.toComposeShape(LocalPaletteShapes.currentValue)
    border(borderStyle.width, color, shape)
}
