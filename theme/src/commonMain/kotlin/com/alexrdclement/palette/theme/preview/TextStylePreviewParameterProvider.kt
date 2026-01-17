package com.alexrdclement.palette.theme.preview

import androidx.compose.ui.text.TextStyle
import com.alexrdclement.palette.theme.PaletteTypography
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TextStylePreviewParameterProvider : PreviewParameterProvider<Pair<String, TextStyle>> {
    override val values = sequenceOf(
        "headline" to PaletteTypography.headline,
        "display" to PaletteTypography.display,
        "titleLarge" to PaletteTypography.titleLarge,
        "titleMedium" to PaletteTypography.titleMedium,
        "titleSmall" to PaletteTypography.titleSmall,
        "labelLarge" to PaletteTypography.labelLarge,
        "labelMedium" to PaletteTypography.labelMedium,
        "labelSmall" to PaletteTypography.labelSmall,
        "bodyLarge" to PaletteTypography.bodyLarge,
        "bodyMedium" to PaletteTypography.bodyMedium,
        "bodySmall" to PaletteTypography.bodySmall,
    )
}
