package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.format.core.TextFormatToken

data class TextStyleScheme(
    val headline: TextStyleTokenSet,
    val display: TextStyleTokenSet,
    val titleLarge: TextStyleTokenSet,
    val titleMedium: TextStyleTokenSet,
    val titleSmall: TextStyleTokenSet,
    val bodySmall: TextStyleTokenSet,
    val bodyMedium: TextStyleTokenSet,
    val bodyLarge: TextStyleTokenSet,
    val labelLarge: TextStyleTokenSet,
    val labelMedium: TextStyleTokenSet,
    val labelSmall: TextStyleTokenSet,
)

fun TextStyleScheme.copy(
    token: TextStyleToken,
    value: TextStyleTokenSet,
) = this.copy(
    headline = if (token == TextStyleToken.Headline) value else this.headline,
    display = if (token == TextStyleToken.Display) value else this.display,
    titleLarge = if (token == TextStyleToken.TitleLarge) value else this.titleLarge,
    titleMedium = if (token == TextStyleToken.TitleMedium) value else this.titleMedium,
    titleSmall = if (token == TextStyleToken.TitleSmall) value else this.titleSmall,
    bodyLarge = if (token == TextStyleToken.BodyLarge) value else this.bodyLarge,
    bodyMedium = if (token == TextStyleToken.BodyMedium) value else this.bodyMedium,
    bodySmall = if (token == TextStyleToken.BodySmall) value else this.bodySmall,
    labelLarge = if (token == TextStyleToken.LabelLarge) value else this.labelLarge,
    labelMedium = if (token == TextStyleToken.LabelMedium) value else this.labelMedium,
    labelSmall = if (token == TextStyleToken.LabelSmall) value else this.labelSmall,
)

val PaletteTextStyleScheme = TextStyleScheme(
    headline = TextStyleTokenSet(
        typographyToken = TypographyToken.Headline,
        textFormatToken = TextFormatToken.Headline,
    ),
    display = TextStyleTokenSet(
        typographyToken = TypographyToken.Display,
        textFormatToken = TextFormatToken.Display,
    ),
    titleLarge = TextStyleTokenSet(
        typographyToken = TypographyToken.TitleLarge,
        textFormatToken = TextFormatToken.Title,
    ),
    titleMedium = TextStyleTokenSet(
        typographyToken = TypographyToken.TitleMedium,
        textFormatToken = TextFormatToken.Title,
    ),
    titleSmall = TextStyleTokenSet(
        typographyToken = TypographyToken.TitleSmall,
        textFormatToken = TextFormatToken.Title,
    ),
    bodyLarge = TextStyleTokenSet(
        typographyToken = TypographyToken.BodyLarge,
        textFormatToken = TextFormatToken.Body,
    ),
    bodyMedium = TextStyleTokenSet(
        typographyToken = TypographyToken.BodyMedium,
        textFormatToken = TextFormatToken.Body,
    ),
    bodySmall = TextStyleTokenSet(
        typographyToken = TypographyToken.BodySmall,
        textFormatToken = TextFormatToken.Body,
    ),
    labelLarge = TextStyleTokenSet(
        typographyToken = TypographyToken.LabelLarge,
        textFormatToken = TextFormatToken.Label,
    ),
    labelMedium = TextStyleTokenSet(
        typographyToken = TypographyToken.LabelMedium,
        textFormatToken = TextFormatToken.Label,
    ),
    labelSmall = TextStyleTokenSet(
        typographyToken = TypographyToken.LabelSmall,
        textFormatToken = TextFormatToken.Label,
    ),
)

@Composable
fun TextStyleScheme.resolve(): ResolvedTextStyleScheme {
    return ResolvedTextStyleScheme(
        headline = headline.toTextStyle(),
        display = display.toTextStyle(),
        titleLarge = titleLarge.toTextStyle(),
        titleMedium = titleMedium.toTextStyle(),
        titleSmall = titleSmall.toTextStyle(),
        bodyLarge = bodyLarge.toTextStyle(),
        bodyMedium = bodyMedium.toTextStyle(),
        bodySmall = bodySmall.toTextStyle(),
        labelLarge = labelLarge.toTextStyle(),
        labelMedium = labelMedium.toTextStyle(),
        labelSmall = labelSmall.toTextStyle(),
    )
}

data class ResolvedTextStyleScheme(
    val headline: TextStyle,
    val display: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodySmall: TextStyle,
    val bodyMedium: TextStyle,
    val bodyLarge: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
)
