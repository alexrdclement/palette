package com.alexrdclement.palette.theme.format

enum class MoneyFormatToken {
    Default,
}

data class MoneyFormatScheme(
    val default: MoneyFormat,
)

fun MoneyFormatToken.toFormat(moneyFormats: MoneyFormatScheme): MoneyFormat {
    return when (this) {
        MoneyFormatToken.Default -> moneyFormats.default
    }
}

fun MoneyFormatScheme.copy(
    token: MoneyFormatToken,
    value: MoneyFormat,
) = this.copy(
    default = if (token == MoneyFormatToken.Default) value else this.default,
)

val PaletteMoneyFormatScheme = MoneyFormatScheme(
    default = MoneyFormat(
        currencySymbol = "$",
        numberFormat = PaletteNumberFormatScheme.default,
    ),
)
