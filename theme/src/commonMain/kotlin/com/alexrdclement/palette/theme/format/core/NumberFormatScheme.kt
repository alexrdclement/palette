package com.alexrdclement.palette.theme.format.core

import com.alexrdclement.palette.formats.core.NumberFormat

data class NumberFormatScheme(
    val default: NumberFormat,
)

fun NumberFormatScheme.copy(
    token: NumberFormatToken,
    value: NumberFormat,
) = this.copy(
    default = if (token == NumberFormatToken.Default) value else this.default,
)

val PaletteNumberFormatScheme = NumberFormatScheme(
    default = NumberFormat(),
)
