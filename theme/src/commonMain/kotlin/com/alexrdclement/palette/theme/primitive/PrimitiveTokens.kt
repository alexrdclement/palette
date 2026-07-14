package com.alexrdclement.palette.theme.primitive

import androidx.compose.foundation.Indication
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily as ComposeFontFamily
import androidx.compose.ui.text.font.FontStyle as ComposeFontStyle
import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight
import com.alexrdclement.palette.components.core.Shape

data class PrimitiveTokens(
    val fontFamily: Map<FontFamily, ComposeFontFamily> =
        FontFamily.entries.associateWith { it.toComposeFontFamily() },
    val fontWeight: Map<FontWeight, ComposeFontWeight> =
        FontWeight.entries.associateWith { it.toComposeFontWeight() },
    val fontStyle: Map<FontStyle, ComposeFontStyle> =
        FontStyle.entries.associateWith { it.toComposeFontStyle() },
    val shape: Map<ShapePrimitiveToken, Shape> =
        ShapePrimitiveToken.entries.associateWith { it.default },
    val indication: Map<IndicationPrimitiveToken, Indication> =
        IndicationPrimitiveToken.entries.associateWith { it.toIndication() },
)

val LocalPrimitiveTokens = staticCompositionLocalOf { PrimitiveTokens() }
