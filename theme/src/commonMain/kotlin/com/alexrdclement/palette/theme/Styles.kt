package com.alexrdclement.palette.theme

import com.alexrdclement.palette.theme.modifiers.BorderStyleScheme
import com.alexrdclement.palette.theme.modifiers.PaletteBorderStyleScheme
import com.alexrdclement.palette.theme.styles.ButtonStyleScheme
import com.alexrdclement.palette.theme.styles.PaletteButtonStyleScheme

data class Styles(
    val border: BorderStyleScheme,
    val buttonStyles: ButtonStyleScheme,
)

val PaletteStyles = Styles(
    border = PaletteBorderStyleScheme,
    buttonStyles = PaletteButtonStyleScheme,
)
