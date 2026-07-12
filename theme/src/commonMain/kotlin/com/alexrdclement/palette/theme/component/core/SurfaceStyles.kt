package com.alexrdclement.palette.theme.component.core

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.SurfaceStyle

object SurfaceStyles {
    val default: SurfaceStyle @Composable get() = SurfaceStyleToken.Default.resolve()
    val container: SurfaceStyle @Composable get() = SurfaceStyleToken.Container.resolve()

    @Composable
    operator fun get(token: SurfaceStyleToken): SurfaceStyle = token.resolve()
}
