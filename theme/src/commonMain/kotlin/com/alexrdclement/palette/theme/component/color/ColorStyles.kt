package com.alexrdclement.palette.theme.component.color

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.color.ColorDisplayStyle
import com.alexrdclement.palette.components.color.ColorPickerControlsStyle
import com.alexrdclement.palette.components.color.ColorPickerDialogContentStyle
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.component.core.BorderStyleToken
import com.alexrdclement.palette.theme.component.core.CoreStyles
import com.alexrdclement.palette.theme.component.core.TextStyles
import com.alexrdclement.palette.theme.component.core.resolve
import com.alexrdclement.palette.theme.component.layout.LayoutStyles
import com.alexrdclement.palette.theme.semantic.shape.ShapeToken
import com.alexrdclement.palette.theme.semantic.shape.toShape

object ColorStyles {

    val colorDisplay: ColorDisplayStyle
        @Composable get() = ColorDisplayStyle(
            shape = ShapeToken.Primary.toShape(),
            borderStyle = BorderStyleToken.Primary.resolve(),
        )

    val colorPicker: ColorPickerStyle
        @Composable get() = ColorPickerStyle(
            spacing = PaletteTheme.semantic.dimension.spacing.medium,
            colorDisplayStyle = colorDisplay,
            controlsStyle = ColorPickerControlsStyle(
                labelStyle = TextStyles.labelLarge,
                sliderStyle = CoreStyles.slider,
                spacing = PaletteTheme.semantic.dimension.spacing.small,
            ),
        )

    val colorPickerDialogContent: ColorPickerDialogContentStyle
        @Composable get() = ColorPickerDialogContentStyle(
            colorPickerStyle = colorPicker,
            confirmCancelButtonRowStyle = LayoutStyles.confirmCancelButtonRow,
            spacing = PaletteTheme.semantic.dimension.spacing.medium,
            padding = PaletteTheme.semantic.dimension.spacing.large,
        )
}
