package com.alexrdclement.palette.components.money

internal actual fun getDecimalFormatSymbols(): DecimalFormatSymbols {
    val symbols = java.text.DecimalFormatSymbols.getInstance()
    return DecimalFormatSymbols(
        currencySymbol = symbols.currencySymbol,
        decimalSeparator = symbols.decimalSeparator,
        groupingSeparator = symbols.groupingSeparator,
    )
}
