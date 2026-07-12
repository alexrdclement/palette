package com.alexrdclement.palette.theme.semantic.format.money

import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.theme.semantic.format.core.PaletteNumberFormatScheme

enum class MoneyFormatToken {
    Default,
}

fun MoneyFormatToken.toFormat(): MoneyFormat {
    return when (this) {
        MoneyFormatToken.Default -> MoneyFormat(
            currencySymbol = "$",
            numberFormat = PaletteNumberFormatScheme.currency,
        )
    }
}
