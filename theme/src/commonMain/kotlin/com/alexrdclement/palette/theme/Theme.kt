package com.alexrdclement.palette.theme

import androidx.compose.foundation.Indication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.PaletteFormats
import com.alexrdclement.palette.theme.format.datetime.DateFormatToken
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatScheme
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatToken
import com.alexrdclement.palette.theme.format.datetime.InstantFormatToken
import com.alexrdclement.palette.theme.format.money.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.format.datetime.TimeFormatToken
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleScheme
import com.alexrdclement.palette.theme.styles.ButtonStyle
import com.alexrdclement.palette.theme.styles.ButtonStyleScheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

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

val LocalPaletteStyles = staticCompositionLocalOf {
    val defaultButtonStyle = ButtonStyle(
        token = ButtonStyleToken.Primary,
        shape = ShapeToken.Primary,
        containerColor = ColorToken.Surface,
        contentColor = ColorToken.Primary,
        borderStyle = null,
    )
    Styles(
        border = BorderStyleScheme(
            primary = BorderStyle(),
            secondary = BorderStyle(),
            tertiary = BorderStyle(),
            surface = BorderStyle(),
        ),
        buttonStyles = ButtonStyleScheme(
            primary = defaultButtonStyle,
            secondary = defaultButtonStyle,
            tertiary = defaultButtonStyle,
        ),
    )
}

val LocalPaletteFormats = staticCompositionLocalOf {
    Formats(
        dateTimeFormats = DateTimeFormatScheme(
            dateFormat = DateFormatToken.YMD,
            timeFormat = TimeFormatToken.HMContinental,
            dateTimeFormat = DateTimeFormatToken.YMDContinental,
            instantFormat = InstantFormatToken.YMDContinental,
        ),
        moneyFormats = MoneyFormatScheme(
            default = MoneyFormat(),
        ),
        numberFormats = NumberFormatScheme(
            default = NumberFormat(),
            currency = NumberFormat(),
        ),
    )
}

@Composable
fun PaletteTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    lightColorScheme: ColorScheme = PaletteLightColorScheme,
    darkColorScheme: ColorScheme = PaletteDarkColorScheme,
    typography: Typography = PaletteTypography,
    shapeScheme: ShapeScheme = PaletteShapeScheme,
    indication: Indication = PaletteIndication,
    spacing: Spacing = PaletteSpacing,
    styles: Styles = PaletteStyles,
    formats: Formats = PaletteFormats,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkMode) darkColorScheme else lightColorScheme
    CompositionLocalProvider(
        LocalPaletteColorScheme provides colorScheme,
        LocalPaletteTypography provides typography,
        LocalPaletteShapes provides shapeScheme,
        LocalPaletteIndication provides indication,
        LocalPaletteSpacing provides spacing,
        LocalPaletteStyles provides styles,
        LocalPaletteFormats provides formats,
        content = content,
    )
}

object PaletteTheme {
    val colorScheme: ColorScheme
        @Composable
        get() = LocalPaletteColorScheme.current

    val formats: Formats
        @Composable
        get() = LocalPaletteFormats.current

    val typography: Typography
        @Composable
        get() = LocalPaletteTypography.current

    val shapeScheme: ShapeScheme
        @Composable
        get() = LocalPaletteShapes.current

    val spacing: Spacing
        @Composable
        get() = LocalPaletteSpacing.current

    val indication: Indication
        @Composable
        get() = LocalPaletteIndication.current

    val styles: Styles
        @Composable
        get() = LocalPaletteStyles.current
}
