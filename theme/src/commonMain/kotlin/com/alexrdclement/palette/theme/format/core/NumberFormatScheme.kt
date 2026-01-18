package com.alexrdclement.palette.theme.format.core

import com.alexrdclement.palette.formats.core.NumberFormat

data class NumberFormatScheme(
    val default: NumberFormat,
    val currency: NumberFormat,
)

fun NumberFormatScheme.copy(
    token: NumberFormatToken,
    value: NumberFormat,
) = this.copy(
    default = if (token == NumberFormatToken.Default) value else this.default,
    currency = if (token == NumberFormatToken.Currency) value else this.currency,
)

val PaletteNumberFormatScheme = NumberFormatScheme(
    default = NumberFormatToken.Default.toFormat(),
    currency = NumberFormatToken.Currency.toFormat(),
)
