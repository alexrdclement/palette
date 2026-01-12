package com.alexrdclement.palette.theme.format

// TODO token-system: move to components.money

data class MoneyFormat(
    val currencySymbol: String? = "$",
    val numberFormat: NumberFormat = NumberFormat(),
)

fun MoneyFormat.update(
    currencySymbol: String? = null,
    numberFormat: NumberFormat? = null,
): MoneyFormat = this.copy(
    currencySymbol = currencySymbol ?: this.currencySymbol,
    numberFormat = numberFormat ?: this.numberFormat,
)
