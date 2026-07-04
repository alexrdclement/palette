package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.components.core.copy
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.Typography
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.format.core.TextFormatScheme
import com.alexrdclement.palette.theme.format.core.TextFormatToken
import com.alexrdclement.palette.theme.format.core.toFormat
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toComposeTextStyle

data class TextStyleTokenSet(
    val typographyToken: TypographyToken,
    val textFormatToken: TextFormatToken,
    val color: ColorToken,
)

@Composable
fun TextStyleTokenSet.toTextStyle(): TextStyle =
    toTextStyle(
        typography = PaletteTheme.typography,
        textFormats = PaletteTheme.formats.textFormats,
    ).copy(color = color.toColor())

fun TextStyleTokenSet.toTextStyle(
    typography: Typography,
    textFormats: TextFormatScheme,
): TextStyle {
    return TextStyle(
        composeTextStyle = typographyToken.toComposeTextStyle(typography),
        format = textFormatToken.toFormat(textFormats),
    )
}
