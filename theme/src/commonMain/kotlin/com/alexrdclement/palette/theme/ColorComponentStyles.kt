package com.alexrdclement.palette.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.color.ColorDisplayStyle
import com.alexrdclement.palette.components.color.ColorPickerControlsStyle
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

/** Resolved styles for [com.alexrdclement.palette.components.color]; surfaced via [PaletteTheme.components]. */
object ColorComponentStyles {

    val colorDisplay: ColorDisplayStyle
        @Composable get() = ColorDisplayStyle(
            shape = PaletteTheme.shapeScheme.primary,
            borderStyle = BorderStyleToken.Primary.toComponentStyle(),
            buttonStyle = CoreComponentStyles.button(ButtonStyleToken.Secondary)
                .copy(contentPadding = PaddingValues(0.dp)),
        )

    val colorPicker: ColorPickerStyle
        @Composable get() = ColorPickerStyle(
            spacing = PaletteTheme.spacing.medium,
            controlsStyle = ColorPickerControlsStyle(
                labelStyle = PaletteTheme.styles.text.labelLarge,
                sliderStyle = CoreComponentStyles.slider,
                spacing = PaletteTheme.spacing.small,
            ),
        )
}
