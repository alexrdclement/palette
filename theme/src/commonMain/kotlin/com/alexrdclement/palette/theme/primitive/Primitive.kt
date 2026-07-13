package com.alexrdclement.palette.theme.primitive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

object Primitive {
    val typography: Typography
        @Composable
        get() = LocalPalettePrimitiveTypography.current

    val shape: ShapePrimitives
        @Composable
        get() = LocalPalettePrimitiveShape.current
}

val LocalPalettePrimitiveTypography = staticCompositionLocalOf {
    PalettePrimitiveTypography
}

val LocalPalettePrimitiveShape = staticCompositionLocalOf {
    PaletteShapePrimitives
}
