package com.alexrdclement.palette.components.money

import com.alexrdclement.palette.components.format.format
import com.alexrdclement.palette.theme.format.MoneyFormat

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
