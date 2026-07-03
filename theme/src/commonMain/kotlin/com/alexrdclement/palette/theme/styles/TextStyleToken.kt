package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.components.core.copy
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.format.core.TextFormatToken
import com.alexrdclement.palette.theme.toColor

enum class TextStyleToken(val default: TextStyleTokenSet) {
    Display(TextStyleTokenSet(TypographyToken.Display, TextFormatToken.Display)),
    Headline(TextStyleTokenSet(TypographyToken.Headline, TextFormatToken.Headline)),
    TitleLarge(TextStyleTokenSet(TypographyToken.TitleLarge, TextFormatToken.Title)),
    TitleMedium(TextStyleTokenSet(TypographyToken.TitleMedium, TextFormatToken.Title)),
    TitleSmall(TextStyleTokenSet(TypographyToken.TitleSmall, TextFormatToken.Title)),
    BodyLarge(TextStyleTokenSet(TypographyToken.BodyLarge, TextFormatToken.Body)),
    BodyMedium(TextStyleTokenSet(TypographyToken.BodyMedium, TextFormatToken.Body)),
    BodySmall(TextStyleTokenSet(TypographyToken.BodySmall, TextFormatToken.Body)),
    LabelLarge(TextStyleTokenSet(TypographyToken.LabelLarge, TextFormatToken.Label)),
    LabelMedium(TextStyleTokenSet(TypographyToken.LabelMedium, TextFormatToken.Label)),
    LabelSmall(TextStyleTokenSet(TypographyToken.LabelSmall, TextFormatToken.Label)),
}

/** The current token set for this token — the theme's current [Styles]. */
@Composable
fun TextStyleToken.tokenSet(): TextStyleTokenSet =
    LocalStyles.current.text.getValue(this)

/** Resolves this token to a component [TextStyle] using the current theme. */
@Composable
fun TextStyleToken.resolve(): TextStyle =
    tokenSet().toTextStyle().copy(color = ColorToken.OnSurface.toColor())
