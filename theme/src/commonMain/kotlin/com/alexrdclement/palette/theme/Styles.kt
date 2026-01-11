package com.alexrdclement.palette.theme

import com.alexrdclement.palette.theme.modifiers.OutlineStyleScheme
import com.alexrdclement.palette.theme.modifiers.PaletteOutlineStyleScheme
import com.alexrdclement.palette.theme.styles.ButtonStyleScheme
import com.alexrdclement.palette.theme.styles.PaletteButtonStyleScheme

data class Styles(
    val buttonStyles: ButtonStyleScheme,
    val outline: OutlineStyleScheme,
)

val PaletteStyles = Styles(
    buttonStyles = PaletteButtonStyleScheme,
    outline = PaletteOutlineStyleScheme,
)
