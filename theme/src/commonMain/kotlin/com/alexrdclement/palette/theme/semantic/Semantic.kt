package com.alexrdclement.palette.theme.semantic

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken
import com.alexrdclement.palette.theme.semantic.color.ColorScheme
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.semantic.format.core.PaletteTextFormatScheme
import com.alexrdclement.palette.theme.semantic.format.datetime.PaletteDateTimeFormats
import com.alexrdclement.palette.theme.semantic.format.money.MoneyFormatScheme
import com.alexrdclement.palette.theme.semantic.indication.NoOpIndication
import com.alexrdclement.palette.theme.semantic.shape.ShapeScheme
import com.alexrdclement.palette.theme.semantic.spacing.Spacing
import com.alexrdclement.palette.theme.semantic.typography.Typography

object Semantic {
    val color: ColorScheme
        @Composable
        get() = LocalPaletteColorScheme.current

    val typography: Typography
        @Composable
        get() = LocalPaletteTypography.current

    val shape: ShapeScheme
        @Composable
        get() = LocalPaletteShapes.current

    val spacing: Spacing
        @Composable
        get() = LocalPaletteSpacing.current

    val indication: Indication
        @Composable
        get() = LocalPaletteIndication.current

    val format: Formats
        @Composable
        get() = LocalPaletteFormats.current
}

val LocalPaletteColorScheme = staticCompositionLocalOf {
    ColorScheme(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        surface = Color.Unspecified,
        onSurface = Color.Unspecified,
        outline = Color.Unspecified,
        disabledContainerAlpha = 1f,
        disabledContentAlpha = 1f,
    )
}

val LocalPaletteTypography = staticCompositionLocalOf {
    Typography(
        headline = TextStyle.Default,
        display = TextStyle.Default,
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleSmall = TextStyle.Default,
        labelLarge = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelSmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodySmall = TextStyle.Default,
    )
}

val LocalPaletteShapes = staticCompositionLocalOf {
    ShapeScheme(
        primary = ShapePrimitiveToken.Rectangle,
        secondary = ShapePrimitiveToken.Rectangle,
        tertiary = ShapePrimitiveToken.Rectangle,
        surface = ShapePrimitiveToken.Rectangle,
    )
}

val LocalPaletteSpacing = staticCompositionLocalOf {
    Spacing(
        xs = Dp.Unspecified,
        small = Dp.Unspecified,
        medium = Dp.Unspecified,
        large = Dp.Unspecified,
    )
}

val LocalPaletteIndication = staticCompositionLocalOf<Indication> {
    NoOpIndication
}

val LocalPaletteFormats = staticCompositionLocalOf {
    Formats(
        dateTimeFormats = PaletteDateTimeFormats,
        moneyFormats = MoneyFormatScheme(
            default = MoneyFormat(),
        ),
        numberFormats = NumberFormatScheme(
            default = NumberFormat(),
            currency = NumberFormat(),
        ),
        textFormats = PaletteTextFormatScheme,
    )
}
