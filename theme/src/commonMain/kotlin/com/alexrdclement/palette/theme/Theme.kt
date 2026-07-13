package com.alexrdclement.palette.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.theme.component.ComponentStyles
import com.alexrdclement.palette.theme.component.ComponentTokens
import com.alexrdclement.palette.theme.component.LocalComponentTokens
import com.alexrdclement.palette.theme.primitive.PaletteShapePrimitives
import com.alexrdclement.palette.theme.primitive.PalettePrimitiveTypography
import com.alexrdclement.palette.theme.primitive.PrimitiveTokens
import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken
import com.alexrdclement.palette.theme.primitive.ShapePrimitives
import com.alexrdclement.palette.theme.primitive.Typography as PrimitiveTypography
import com.alexrdclement.palette.theme.semantic.ColorScheme
import com.alexrdclement.palette.theme.semantic.NoOpIndication
import com.alexrdclement.palette.theme.semantic.SemanticTokens
import com.alexrdclement.palette.theme.semantic.ShapeScheme
import com.alexrdclement.palette.theme.semantic.Spacing
import com.alexrdclement.palette.theme.semantic.Typography
import com.alexrdclement.palette.theme.semantic.resolve
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.semantic.format.core.PaletteTextFormatScheme
import com.alexrdclement.palette.theme.semantic.format.datetime.PaletteDateTimeFormats
import com.alexrdclement.palette.theme.semantic.format.money.MoneyFormatScheme

val LocalPalettePrimitiveTypography = staticCompositionLocalOf {
    PalettePrimitiveTypography
}

val LocalPalettePrimitiveShape = staticCompositionLocalOf {
    PaletteShapePrimitives
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

@Composable
fun PaletteTheme(
    primitive: PrimitiveTokens = PrimitiveTokens(),
    semantic: SemanticTokens = SemanticTokens(),
    component: ComponentTokens = ComponentTokens(),
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkMode) semantic.colors.dark else semantic.colors.light
    val typography = remember(primitive.typography, semantic.typography) {
        semantic.typography.resolve(primitive.typography)
    }
    CompositionLocalProvider(
        LocalPalettePrimitiveTypography provides primitive.typography,
        LocalPalettePrimitiveShape provides primitive.shape,
        LocalPaletteColorScheme provides colorScheme,
        LocalPaletteTypography provides typography,
        LocalPaletteShapes provides semantic.shapeScheme,
        LocalPaletteIndication provides semantic.indication,
        LocalPaletteSpacing provides semantic.spacing,
        LocalComponentTokens provides component,
        LocalPaletteFormats provides semantic.formats,
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
 *
 * These accessors expose the **resolved** values components consume, which differ from the editable
 * inputs of the same tier: [semantic].[Semantic.color] is the active [ColorScheme] resolved from the
 * [com.alexrdclement.palette.theme.semantic.SemanticTokens.colors] light/dark pair and the dark-mode
 * flag, and [semantic].[Semantic.typography] is the [Typography] ramp resolved from the primitive
 * font plus the [com.alexrdclement.palette.theme.semantic.SemanticTypography] overrides. Edit the
 * inputs via the token holders (see [PaletteTheme]); read the resolved values here.
 */
object PaletteTheme {
    val primitive get() = Primitive
    val semantic get() = Semantic
    val component get() = ComponentStyles

    object Primitive {
        val typography: PrimitiveTypography
            @Composable
            get() = LocalPalettePrimitiveTypography.current

        val shape: ShapePrimitives
            @Composable
            get() = LocalPalettePrimitiveShape.current
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
