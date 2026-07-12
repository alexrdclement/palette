package com.alexrdclement.palette.theme.semantic

import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import com.alexrdclement.palette.theme.primitive.Typography as PrimitiveTypography

/**
 * Semantic typography tokens, modeled as a base ramp derived from the primitive font family/weight
 * plus per-token [overrides]. A primitive change flows through every non-overridden token; an
 * overridden token stays pinned to its explicit style. Resolved to a concrete [Typography] ramp via
 * [resolve].
 */
data class SemanticTypography(
    val overrides: Map<TypographyToken, ComposeTextStyle> = emptyMap(),
) {
    fun withOverride(token: TypographyToken, textStyle: ComposeTextStyle): SemanticTypography =
        copy(overrides = overrides + (token to textStyle))
}

fun SemanticTypography.resolve(primitiveTypography: PrimitiveTypography): Typography {
    var typography = makePaletteTypography(primitiveTypography = primitiveTypography)
    overrides.forEach { (token, textStyle) ->
        typography = typography.copy(token = token, textStyle = textStyle)
    }
    return typography
}
