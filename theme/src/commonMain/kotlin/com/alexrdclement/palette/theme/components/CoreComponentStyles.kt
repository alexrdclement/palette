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
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.contentColorFor
import com.alexrdclement.palette.theme.styles.ResolvedButtonStyleScheme
import com.alexrdclement.palette.theme.styles.resolve
import com.alexrdclement.palette.theme.toColor

/** Resolved styles for [com.alexrdclement.palette.components.core]; surfaced via [PaletteTheme.components]. */
object CoreComponentStyles {

    /** Resolved button styles per variant, e.g. `button.primary`; index by token with `button[token]`. */
    val button: ResolvedButtonStyleScheme
        @Composable get() = PaletteTheme.styles.button.resolve()

    val surface: SurfaceStyle
        @Composable get() = SurfaceStyle(
            shape = PaletteTheme.shapeScheme.surface,
            color = PaletteTheme.colorScheme.surface,
            contentColor = PaletteTheme.colorScheme.contentColorFor(PaletteTheme.colorScheme.surface),
            indication = PaletteTheme.indication,
        )

    val divider: DividerStyle
        @Composable get() = DividerStyle(
            color = PaletteTheme.colorScheme.outline,
        )

    val progressIndicator: ProgressIndicatorStyle
        @Composable get() = ProgressIndicatorStyle(
            textStyle = PaletteTheme.styles.text.bodyMedium,
        )

    val checkbox: CheckboxStyle
        @Composable get() = CheckboxStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
                indication = PaletteTheme.indication,
            ),
            textStyle = PaletteTheme.styles.text.titleLarge,
        )

    val chevronButton: ChevronButtonStyle
        @Composable get() = ChevronButtonStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
                indication = PaletteTheme.indication,
            ),
            iconColor = PaletteTheme.colorScheme.primary,
            contentPadding = PaddingValues(PaletteTheme.spacing.medium),
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
            textStyle = PaletteTheme.styles.text.bodyMedium,
            cursorBrush = SolidColor(PaletteTheme.colorScheme.primary),
            borderStroke = BorderStroke(1.dp, PaletteTheme.colorScheme.outline),
            contentPadding = PaletteTheme.spacing.small,
        )
}
