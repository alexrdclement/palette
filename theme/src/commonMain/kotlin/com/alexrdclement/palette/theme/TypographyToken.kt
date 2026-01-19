package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle as ComposeTextStyle

enum class TypographyToken {
    Display,
    Headline,
    TitleLarge,
    TitleMedium,
    TitleSmall,
    BodyLarge,
    BodyMedium,
    BodySmall,
    LabelLarge,
    LabelMedium,
    LabelSmall,
}

@Composable
fun TypographyToken.toComposeTextStyle(): ComposeTextStyle {
    return this.toComposeTextStyle(PaletteTheme.typography)
}

fun TypographyToken.toComposeTextStyle(typography: Typography): ComposeTextStyle {
    return when (this) {
        TypographyToken.Display -> typography.display
        TypographyToken.Headline -> typography.headline
        TypographyToken.TitleLarge -> typography.titleLarge
        TypographyToken.TitleMedium -> typography.titleMedium
        TypographyToken.TitleSmall -> typography.titleSmall
        TypographyToken.BodyLarge -> typography.bodyLarge
        TypographyToken.BodyMedium -> typography.bodyMedium
        TypographyToken.BodySmall -> typography.bodySmall
        TypographyToken.LabelLarge -> typography.labelLarge
        TypographyToken.LabelMedium -> typography.labelMedium
        TypographyToken.LabelSmall -> typography.labelSmall
    }
}
