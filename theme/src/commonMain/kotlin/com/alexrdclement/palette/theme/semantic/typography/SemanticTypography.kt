package com.alexrdclement.palette.theme.semantic.typography

import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import com.alexrdclement.palette.theme.primitive.Typography as PrimitiveTypography

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
