package com.alexrdclement.palette.theme.component.core

import androidx.compose.foundation.BorderStroke
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
import com.alexrdclement.palette.theme.semantic.color.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.dimensions.PaddingValueToken
import com.alexrdclement.palette.theme.semantic.dimensions.toPaddingValues
import com.alexrdclement.palette.theme.semantic.shape.ShapeToken
import com.alexrdclement.palette.theme.semantic.color.toColor
import com.alexrdclement.palette.theme.semantic.shape.toShape

object CoreStyles {
    val text get() = TextStyles

    val button get() = ButtonStyles

    val border get() = BorderStyles

    val surface get() = SurfaceStyles

    val divider: DividerStyle
        @Composable get() = DividerStyle(
            color = PaletteTheme.semantic.color.outline,
        )

    val progressIndicator: ProgressIndicatorStyle
        @Composable get() = ProgressIndicatorStyle(
            textStyle = text.bodyMedium,
        )

    val checkbox: CheckboxStyle
        @Composable get() = CheckboxStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Surface.toColor(),
                disabledContentAlpha = PaletteTheme.semantic.color.disabledContentAlpha,
                disabledContainerAlpha = PaletteTheme.semantic.color.disabledContainerAlpha,
                indication = PaletteTheme.semantic.indication,
            ),
            textStyle = text.titleLarge,
        )

    val chevronButton: ChevronButtonStyle
        @Composable get() = ChevronButtonStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Surface.toColor(),
                shape = ShapeToken.Primary.toShape(),
                contentPadding = PaddingValueToken.Compact.toPaddingValues(),
                disabledContentAlpha = PaletteTheme.semantic.color.disabledContentAlpha,
                disabledContainerAlpha = PaletteTheme.semantic.color.disabledContainerAlpha,
                indication = PaletteTheme.semantic.indication,
            ),
            iconColor = PaletteTheme.semantic.color.primary,
        )

    val chevronIcon: ChevronIconStyle
        @Composable get() = ChevronIconStyle(
            color = PaletteTheme.semantic.color.primary,
        )

    val slider: SliderStyle
        @Composable get() = SliderStyle(
            colors = SliderColors(
                trackColor = PaletteTheme.semantic.color.primary,
                thumbColor = PaletteTheme.semantic.color.primary,
                thumbPointColor = PaletteTheme.semantic.color.primary,
                thumbBackgroundColor = PaletteTheme.semantic.color.surface,
            ),
        )

    val textField: TextFieldStyle
        @Composable get() = TextFieldStyle(
            textStyle = text.bodyMedium,
            cursorBrush = SolidColor(PaletteTheme.semantic.color.primary),
            borderStroke = BorderStroke(1.dp, PaletteTheme.semantic.color.outline),
            contentPadding = PaletteTheme.semantic.dimensions.spacing.small,
        )
}
