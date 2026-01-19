package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.styles.ResolvedTextStyleScheme
import com.alexrdclement.palette.theme.styles.TextStyle
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

@Composable
fun TypographyToken.toStyle(): TextStyle {
    return toStyle(PaletteTheme.styles.text)
}

fun TypographyToken.toStyle(resolvedTextStyleScheme: ResolvedTextStyleScheme): TextStyle {
    return when (this) {
        TypographyToken.Display -> resolvedTextStyleScheme.display
        TypographyToken.Headline -> resolvedTextStyleScheme.headline
        TypographyToken.TitleLarge -> resolvedTextStyleScheme.titleLarge
        TypographyToken.TitleMedium -> resolvedTextStyleScheme.titleMedium
        TypographyToken.TitleSmall -> resolvedTextStyleScheme.titleSmall
        TypographyToken.BodyLarge -> resolvedTextStyleScheme.bodyLarge
        TypographyToken.BodyMedium -> resolvedTextStyleScheme.bodyMedium
        TypographyToken.BodySmall -> resolvedTextStyleScheme.bodySmall
        TypographyToken.LabelLarge -> resolvedTextStyleScheme.labelLarge
        TypographyToken.LabelMedium -> resolvedTextStyleScheme.labelMedium
        TypographyToken.LabelSmall -> resolvedTextStyleScheme.labelSmall
    }
}
