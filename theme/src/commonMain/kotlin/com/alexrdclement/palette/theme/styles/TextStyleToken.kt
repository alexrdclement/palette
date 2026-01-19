package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme

enum class TextStyleToken {
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
fun TextStyleToken.toStyle(): TextStyleTokenSet {
    return toStyle(PaletteTheme.styles.textStyleScheme)
}

fun TextStyleToken.toStyle(textStyleScheme: TextStyleScheme): TextStyleTokenSet {
    return when (this) {
        TextStyleToken.Display -> textStyleScheme.display
        TextStyleToken.Headline -> textStyleScheme.headline
        TextStyleToken.TitleLarge -> textStyleScheme.titleLarge
        TextStyleToken.TitleMedium -> textStyleScheme.titleMedium
        TextStyleToken.TitleSmall -> textStyleScheme.titleSmall
        TextStyleToken.BodyLarge -> textStyleScheme.bodyLarge
        TextStyleToken.BodyMedium -> textStyleScheme.bodyMedium
        TextStyleToken.BodySmall -> textStyleScheme.bodySmall
        TextStyleToken.LabelLarge -> textStyleScheme.labelLarge
        TextStyleToken.LabelMedium -> textStyleScheme.labelMedium
        TextStyleToken.LabelSmall -> textStyleScheme.labelSmall
    }
}
