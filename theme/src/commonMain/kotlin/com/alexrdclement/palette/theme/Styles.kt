package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.modifiers.BorderStyleScheme
import com.alexrdclement.palette.theme.modifiers.PaletteBorderStyleScheme
import com.alexrdclement.palette.theme.styles.ButtonStyleScheme
import com.alexrdclement.palette.theme.styles.PaletteButtonStyleScheme
import com.alexrdclement.palette.theme.styles.PaletteTextStyleScheme
import com.alexrdclement.palette.theme.styles.ResolvedTextStyleScheme
import com.alexrdclement.palette.theme.styles.TextStyleScheme
import com.alexrdclement.palette.theme.styles.resolve

class Styles(
    val border: BorderStyleScheme,
    val button: ButtonStyleScheme,
    val textStyleScheme: TextStyleScheme,
) {
    val text: ResolvedTextStyleScheme
        @Composable
        get() = textStyleScheme.resolve()

    fun copy(
        border: BorderStyleScheme = this.border,
        button: ButtonStyleScheme = this.button,
        text: TextStyleScheme = this.textStyleScheme,
    ): Styles {
        return Styles(
            border = border,
            button = button,
            textStyleScheme = text,
        )
    }
}

val PaletteStyles = Styles(
    border = PaletteBorderStyleScheme,
    button = PaletteButtonStyleScheme,
    textStyleScheme = PaletteTextStyleScheme,
)
