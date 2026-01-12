package com.alexrdclement.palette.theme.format

// TODO token-system: move to components or somewhere it can still see it

data class NumberFormat(
    val numDecimalValuesRange: IntRange = 0..2,
    val positiveSign: String? = null,
    val negativeSign: String = "-",
    val decimalSeparator: Char = '.',
    val groupingSeparator: Char = ',',
    val groupingChunk: Int = 3,
) {
    val minNumDecimalValues: Int
        get() = numDecimalValuesRange.first

    val maxNumDecimalValues: Int
        get() = numDecimalValuesRange.last
}

fun NumberFormat.update(
    numDecimalValuesRange: IntRange? = null,
    positiveSign: String? = null,
    negativeSign: String? = null,
    decimalSeparator: Char? = null,
    groupingSeparator: Char? = null,
    groupingChunk: Int? = null,
): NumberFormat = this.copy(
    numDecimalValuesRange = numDecimalValuesRange ?: this.numDecimalValuesRange,
    positiveSign = positiveSign ?: this.positiveSign,
    negativeSign = negativeSign ?: this.negativeSign,
    decimalSeparator = decimalSeparator ?: this.decimalSeparator,
    groupingSeparator = groupingSeparator ?: this.groupingSeparator,
    groupingChunk = groupingChunk ?: this.groupingChunk,
)
