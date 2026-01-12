package com.alexrdclement.palette.theme.format

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme

enum class NumberFormatToken {
    Default,
}

data class NumberFormatScheme(
    val default: NumberFormat,
)

fun NumberFormatToken.toFormat(numberFormats: NumberFormatScheme): NumberFormat {
    return when (this) {
        NumberFormatToken.Default -> numberFormats.default
    }
}

@Composable
fun NumberFormatToken.toFormat(): NumberFormat {
    return toFormat(PaletteTheme.formats.numberFormats)
}

fun NumberFormatScheme.copy(
    token: NumberFormatToken,
    value: NumberFormat,
) = this.copy(
    default = if (token == NumberFormatToken.Default) value else this.default,
)

val PaletteNumberFormatScheme = NumberFormatScheme(
    default = NumberFormat(
        decimalSeparator = '.',
        groupingSeparator = ',',
    ),
)
