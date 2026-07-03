package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

enum class SurfaceStyleToken(val default: SurfaceStyleTokenSet) {
    Default(
        SurfaceStyleTokenSet(
            color = ColorToken.Surface,
            shape = ShapeToken.Surface,
            borderStyle = null,
        ),
    ),
    Container(
        SurfaceStyleTokenSet(
            color = ColorToken.Surface,
            shape = ShapeToken.Surface,
            borderStyle = BorderStyleToken.Surface,
        ),
    ),
}

/** The current token set for this token — the theme's current [Styles]. */
@Composable
fun SurfaceStyleToken.tokenSet(): SurfaceStyleTokenSet =
    LocalStyles.current.surface.getValue(this)

/** Resolves this token to a component [SurfaceStyle] using the current theme. */
@Composable
fun SurfaceStyleToken.resolve(): SurfaceStyle = tokenSet().toComponentStyle()
