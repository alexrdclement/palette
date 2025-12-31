package com.alexrdclement.palette.components.money

internal actual fun getDecimalFormatSymbols(): DecimalFormatSymbols {
    return DecimalFormatSymbols(
        currencySymbol = "$",
        decimalSeparator = '.',
        groupingSeparator = ',',
    )
}
