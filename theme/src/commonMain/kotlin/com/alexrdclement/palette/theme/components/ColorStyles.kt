package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.color.ColorDisplayStyle
import com.alexrdclement.palette.components.color.ColorPickerControlsStyle
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.BorderStyleToken
import com.alexrdclement.palette.theme.styles.resolve

object ColorStyles {

    val colorDisplay: ColorDisplayStyle
        @Composable get() = ColorDisplayStyle(
            shape = PaletteTheme.shapeScheme.primary,
            borderStyle = BorderStyleToken.Primary.resolve(),
        )

    val colorPicker: ColorPickerStyle
        @Composable get() = ColorPickerStyle(
            spacing = PaletteTheme.spacing.medium,
            colorDisplayStyle = colorDisplay,
            controlsStyle = ColorPickerControlsStyle(
                labelStyle = TextStyles.labelLarge,
                sliderStyle = CoreStyles.slider,
                spacing = PaletteTheme.spacing.small,
            ),
        )
}
