package com.alexrdclement.palette.theme.semantic.typography

import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import androidx.compose.ui.unit.TextUnit
import com.alexrdclement.palette.theme.primitive.FontFamily
import com.alexrdclement.palette.theme.primitive.FontStyle
import com.alexrdclement.palette.theme.primitive.FontWeight
import com.alexrdclement.palette.theme.primitive.PrimitiveTokens

/**
 * A semantic typography token's selection of primitive font tokens plus its own metrics. Each
 * semantic token picks which primitive [fontFamily], [fontWeight], and [fontStyle] it uses,
 * resolving those selections through the theme's [PrimitiveTokens].
 */
data class TypographyTokenSet(
    val fontFamily: FontFamily,
    val fontWeight: FontWeight,
    val fontStyle: FontStyle,
    val fontSize: TextUnit,
    val lineHeight: TextUnit,
    val letterSpacing: TextUnit,
)

fun TypographyTokenSet.toComposeTextStyle(primitiveTokens: PrimitiveTokens): ComposeTextStyle =
    ComposeTextStyle(
        fontFamily = primitiveTokens.fontFamily.getValue(fontFamily),
        fontWeight = primitiveTokens.fontWeight.getValue(fontWeight),
        fontStyle = primitiveTokens.fontStyle.getValue(fontStyle),
        fontSize = fontSize,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
    )
