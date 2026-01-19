package com.alexrdclement.palette.theme.format.core

import com.alexrdclement.palette.formats.core.NumberFormat

enum class NumberFormatToken {
    Default,
    Currency,
}

fun NumberFormatToken.toFormat(): NumberFormat {
    return when (this) {
        NumberFormatToken.Default -> NumberFormat()
        NumberFormatToken.Currency -> NumberFormat(
            numDecimalValuesRange = 2..2,
        )
    }
}
