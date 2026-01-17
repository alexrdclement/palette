package com.alexrdclement.palette.formats.money

import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.formats.core.format

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

fun MoneyFormat.format(
    amount: Double,
): String {
    val formattedNumber = numberFormat.format(amount)

    return if (currencySymbol != null) {
        "$currencySymbol$formattedNumber"
    } else {
        formattedNumber
    }
}
