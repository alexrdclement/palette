package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.color.ColorDisplayStyle
import com.alexrdclement.palette.components.color.ColorPickerControlsStyle
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

/** Resolved styles for [com.alexrdclement.palette.components.color]; surfaced via [PaletteTheme.styles]. */
object ColorStyles {

    val colorDisplay: ColorDisplayStyle
        @Composable get() = ColorDisplayStyle(
            shape = PaletteTheme.shapeScheme.primary,
            borderStyle = BorderStyleToken.Primary.toComponentStyle(),
            buttonStyle = CoreStyles.button.secondary
                .copy(contentPadding = PaddingValues(0.dp)),
        )

    val colorPicker: ColorPickerStyle
        @Composable get() = ColorPickerStyle(
            spacing = PaletteTheme.spacing.medium,
            controlsStyle = ColorPickerControlsStyle(
                labelStyle = TextStyles.labelLarge,
                sliderStyle = CoreStyles.slider,
                spacing = PaletteTheme.spacing.small,
            ),
        )
}
