package com.alexrdclement.palette.theme

import com.alexrdclement.palette.theme.styles.ButtonStyleScheme
import com.alexrdclement.palette.theme.styles.PaletteButtonStyleScheme

data class Styles(
    val buttonStyles: ButtonStyleScheme,
)

val PaletteStyles = Styles(
    buttonStyles = PaletteButtonStyleScheme,
)
