package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.CheckboxStyle
import com.alexrdclement.palette.components.core.ChevronButtonStyle
import com.alexrdclement.palette.components.core.ChevronIconStyle
import com.alexrdclement.palette.components.core.DividerStyle
import com.alexrdclement.palette.components.core.ProgressIndicatorStyle
import com.alexrdclement.palette.components.core.SliderColors
import com.alexrdclement.palette.components.core.SliderStyle
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape

object CoreStyles {
    val text get() = TextStyles

    val button get() = ButtonStyles

    val border get() = BorderStyles

    val surface get() = SurfaceStyles

    val divider: DividerStyle
        @Composable get() = DividerStyle(
            color = PaletteTheme.colorScheme.outline,
        )

    val progressIndicator: ProgressIndicatorStyle
        @Composable get() = ProgressIndicatorStyle(
            textStyle = text.bodyMedium,
        )

    val checkbox: CheckboxStyle
        @Composable get() = CheckboxStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Surface.toColor(),
                indication = PaletteTheme.indication,
            ),
            textStyle = text.titleLarge,
        )

    val chevronButton: ChevronButtonStyle
        @Composable get() = ChevronButtonStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Surface.toColor(),
                shape = ShapeToken.Primary.toShape(),
                contentPadding = PaddingValues(PaletteTheme.spacing.medium),
                indication = PaletteTheme.indication,
            ),
            iconColor = PaletteTheme.colorScheme.primary,
        )

    val chevronIcon: ChevronIconStyle
        @Composable get() = ChevronIconStyle(
            color = PaletteTheme.colorScheme.primary,
        )

    val slider: SliderStyle
        @Composable get() = SliderStyle(
            colors = SliderColors(
                trackColor = PaletteTheme.colorScheme.primary,
                thumbColor = PaletteTheme.colorScheme.primary,
                thumbPointColor = PaletteTheme.colorScheme.primary,
                thumbBackgroundColor = PaletteTheme.colorScheme.surface,
            ),
        )

    val textField: TextFieldStyle
        @Composable get() = TextFieldStyle(
            textStyle = text.bodyMedium,
            cursorBrush = SolidColor(PaletteTheme.colorScheme.primary),
            borderStroke = BorderStroke(1.dp, PaletteTheme.colorScheme.outline),
            contentPadding = PaletteTheme.spacing.small,
        )
}
