package com.alexrdclement.palette.theme.component.core

import com.alexrdclement.palette.theme.component.LocalComponentTokens

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.theme.semantic.ColorToken
import com.alexrdclement.palette.theme.semantic.ShapeToken

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

@Composable
fun SurfaceStyleToken.tokenSet(): SurfaceStyleTokenSet =
    LocalComponentTokens.current.surface.getValue(this)

@Composable
fun SurfaceStyleToken.resolve(): SurfaceStyle = tokenSet().toComponentStyle()
