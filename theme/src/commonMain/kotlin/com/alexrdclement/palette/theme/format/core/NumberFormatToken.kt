package com.alexrdclement.palette.theme.format.core

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.theme.PaletteTheme

enum class NumberFormatToken {
    Default,
}

fun NumberFormatToken.toFormat(numberFormats: NumberFormatScheme): NumberFormat {
    return when (this) {
        NumberFormatToken.Default -> numberFormats.default
    }
}

@Composable
fun NumberFormatToken.toFormat(): NumberFormat {
    return toFormat(PaletteTheme.formats.numberFormats)
}
