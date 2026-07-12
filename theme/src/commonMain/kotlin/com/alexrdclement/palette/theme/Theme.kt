package com.alexrdclement.palette.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.theme.component.ComponentStyles
import com.alexrdclement.palette.theme.component.core.LocalStyles
import com.alexrdclement.palette.theme.component.core.Styles
import com.alexrdclement.palette.theme.primitive.PaletteShapePrimitives
import com.alexrdclement.palette.theme.primitive.PalettePrimitiveTypography
import com.alexrdclement.palette.theme.primitive.Typography as PrimitiveTypography
import com.alexrdclement.palette.theme.semantic.ColorScheme
import com.alexrdclement.palette.theme.semantic.NoOpIndication
import com.alexrdclement.palette.theme.semantic.PaletteDarkColorScheme
import com.alexrdclement.palette.theme.semantic.PaletteIndication
import com.alexrdclement.palette.theme.semantic.PaletteLightColorScheme
import com.alexrdclement.palette.theme.semantic.PaletteShapeScheme
import com.alexrdclement.palette.theme.semantic.PaletteSpacing
import com.alexrdclement.palette.theme.semantic.PaletteTypography
import com.alexrdclement.palette.theme.semantic.ShapeScheme
import com.alexrdclement.palette.theme.semantic.Spacing
import com.alexrdclement.palette.theme.semantic.Typography
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.format.PaletteFormats
import com.alexrdclement.palette.theme.semantic.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.semantic.format.core.PaletteTextFormatScheme
import com.alexrdclement.palette.theme.semantic.format.datetime.PaletteDateTimeFormats
import com.alexrdclement.palette.theme.semantic.format.money.MoneyFormatScheme

val LocalPalettePrimitiveTypography = staticCompositionLocalOf {
    PalettePrimitiveTypography
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
        headline = ComposeTextStyle.Default,
        display = ComposeTextStyle.Default,
        titleLarge = ComposeTextStyle.Default,
        titleMedium = ComposeTextStyle.Default,
        titleSmall = ComposeTextStyle.Default,
        labelLarge = ComposeTextStyle.Default,
        labelMedium = ComposeTextStyle.Default,
        labelSmall = ComposeTextStyle.Default,
        bodyLarge = ComposeTextStyle.Default,
        bodyMedium = ComposeTextStyle.Default,
        bodySmall = ComposeTextStyle.Default,
    )
}

val LocalPaletteShapes = staticCompositionLocalOf {
    ShapeScheme(
        primary = Shape.Rectangle(),
        secondary = Shape.Rectangle(),
        tertiary = Shape.Rectangle(),
        surface = Shape.Rectangle(),
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

@Composable
fun PaletteTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    primitiveTypography: PrimitiveTypography = PalettePrimitiveTypography,
    lightColorScheme: ColorScheme = PaletteLightColorScheme,
    darkColorScheme: ColorScheme = PaletteDarkColorScheme,
    typography: Typography = PaletteTypography,
    shapeScheme: ShapeScheme = PaletteShapeScheme,
    indication: Indication = PaletteIndication,
    spacing: Spacing = PaletteSpacing,
    styles: Styles = Styles(),
    formats: Formats = PaletteFormats,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkMode) darkColorScheme else lightColorScheme
    CompositionLocalProvider(
        LocalPalettePrimitiveTypography provides primitiveTypography,
        LocalPaletteColorScheme provides colorScheme,
        LocalPaletteTypography provides typography,
        LocalPaletteShapes provides shapeScheme,
        LocalPaletteIndication provides indication,
        LocalPaletteSpacing provides spacing,
        LocalStyles provides styles,
        LocalPaletteFormats provides formats,
        content = content,
    )
}

/**
 * Theme accessors, grouped into the three design-token tiers:
 *
 * - [primitive]: unopinionated literal building blocks (font family/weight, raw shapes).
 * - [semantic]: design-intent tokens that reference primitives (color, typography ramp, shape
 *   scheme, spacing, indication, formats).
 * - [component]: tokens that map closely to components (button, surface, text styles, ...).
 */
object PaletteTheme {
    val primitive get() = Primitive
    val semantic get() = Semantic
    val component get() = ComponentStyles

    object Primitive {
        val typography: PrimitiveTypography
            @Composable
            get() = LocalPalettePrimitiveTypography.current

        val shape get() = PaletteShapePrimitives
    }

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
}
