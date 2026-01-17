package com.alexrdclement.palette.formats.core

import androidx.annotation.CheckResult
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.delete
import androidx.compose.ui.text.input.KeyboardType
import kotlin.math.min
import kotlin.text.iterator

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

fun NumberFormat.format(
    amount: Double,
): String {
    return format(amount.toString())
}

private const val DOUBLE_VALUE_INFINITY_LOWERCASE = "infinity"
private const val DOUBLE_VALUE_NEGATIVE_INFINITY_LOWERCASE = "-infinity"
private const val DOUBLE_VALUE_NAN_LOWERCASE = "nan"
private const val DOUBLE_VALUE_SCIENTIFIC_NOTATION_CHAR_LOWERCASE = 'e'
private const val MINUS_SIGN_ASCII = '-'
private const val MINUS_SIGN_UNICODE = '−'
private const val PLUS_SIGN_ASCII = '+'
private const val PLUS_SIGN_UNICODE = '＋'

fun NumberFormat.format(
    amount: String,
): String {
    if (amount.isEmpty()) return ""

    // Handle special values and scientific notation as-is
    val amountLower = amount.lowercase()
    if (amountLower.contains(DOUBLE_VALUE_SCIENTIFIC_NOTATION_CHAR_LOWERCASE) ||
        amountLower == DOUBLE_VALUE_INFINITY_LOWERCASE ||
        amountLower == DOUBLE_VALUE_NEGATIVE_INFINITY_LOWERCASE ||
        amountLower == DOUBLE_VALUE_NAN_LOWERCASE) {
        return amount
    }

    val isNegative = amount.startsWith(MINUS_SIGN_ASCII) || amount.startsWith(MINUS_SIGN_UNICODE)

    val absAmountStr = amount.trimStart(MINUS_SIGN_ASCII, MINUS_SIGN_UNICODE, PLUS_SIGN_ASCII, PLUS_SIGN_UNICODE)
    val numericValue = absAmountStr.toDoubleOrNull() ?: 0.0

    val sign = when {
        isNegative -> negativeSign
        numericValue == 0.0 -> ""
        else -> positiveSign.orEmpty()
    }

    val (rawIntPart, fracPart) = splitNumberParts(absAmountStr)
    val intPart = normalizeIntegerPart(rawIntPart)

    return format(
        sign = sign,
        intPart = intPart,
        fracPart = fracPart,
    )
}

fun NumberFormat.format(
    sign: String = "",
    intPart: String,
    fracPart: String? = null,
): String {
    val decimalPart = if (fracPart != null || minNumDecimalValues > 0) {
        val truncated = (fracPart ?: "").take(maxNumDecimalValues)

        // Trim trailing zeros but keep at least minNumDecimalValues
        val trimmed = truncated.trimEnd('0')
        if (trimmed.length < minNumDecimalValues) {
            trimmed.padEnd(minNumDecimalValues, '0')
        } else if (trimmed.isEmpty() && minNumDecimalValues == 0) {
            null
        } else {
            trimmed
        }
    } else {
        null
    }

    val formattedIntPart = applyGroupingSeparator(intPart, groupingSeparator, groupingChunk)

    return if (decimalPart != null) {
        val decimalSeparatorStr = decimalSeparator.toString()
        "$sign$formattedIntPart$decimalSeparatorStr$decimalPart"
    } else {
        "$sign$formattedIntPart"
    }
}

class NumberFormatInputTransformation(
    numberFormat: NumberFormat,
    private val maxNumDecimalValues: Int? = null,
) : InputTransformation {

    private val decimalSeparator = numberFormat.decimalSeparator

    override val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Decimal,
    )

    override fun TextFieldBuffer.transformInput() {
        filterChars()
        filterConsecutiveDecimals()

        val (intPart, decimalPart) = splitNumberParts(asCharSequence().toString(), decimalSeparator)

        val numSeparators = asCharSequence().count { it == decimalSeparator }
        if (numSeparators > 1) {
            // Instead of rejecting changes with multiple decimals, recalculate according to the
            // first one.
            replace(intPart.length + 1, length, decimalPart ?: "")
        }

        val filteredIntPart = filterIntPart(intPart, hasDecimalPart = decimalPart != null)

        filterDecimalPart(decimalPart, startIndex = filteredIntPart.length + 1)
    }

    private fun TextFieldBuffer.filterChars() {
        val proposed = asCharSequence()
        if (proposed.any { !it.isDigit() && it != decimalSeparator }) {
            // Reject changes for any non-digit, non-decimal characters
            revertAllChanges()
        }
    }

    private fun TextFieldBuffer.filterConsecutiveDecimals() {
        val proposed = asCharSequence()
        var prevChar = proposed.firstOrNull()
        for (char in proposed.drop(1)) {
            if (prevChar == decimalSeparator && char == decimalSeparator) {
                // Reject changes for consecutive decimals
                revertAllChanges()
                return
            }
            prevChar = char
        }
    }

    @CheckResult
    private fun TextFieldBuffer.filterIntPart(
        intPart: String,
        hasDecimalPart: Boolean,
    ): String {
        var mutableIntPart = intPart

        if (mutableIntPart.startsWith('0')) {
            // Allow single leading zero. Replace leading zero if followed by another digit.
            val newIntPart = normalizeIntegerPart(mutableIntPart)
            replace(0, mutableIntPart.length, newIntPart)
            mutableIntPart = newIntPart
        }

        if (mutableIntPart.isEmpty() && hasDecimalPart) {
            // Prefill 0 when only decimal part is entered
            val newIntPart = "0"
            replace(0, mutableIntPart.length, newIntPart)
            mutableIntPart = newIntPart
        }

        return mutableIntPart
    }

    private fun TextFieldBuffer.filterDecimalPart(
        decimalPart: String?,
        startIndex: Int,
    ) {
        if (decimalPart == null) return
        if (maxNumDecimalValues == null) return

        if (decimalPart.length > maxNumDecimalValues) {
            // Replace chars one-by-one so the cursor advances as expected
            for (index in startIndex until min(maxNumDecimalValues, decimalPart.length)) {
                replace(index, index + 1, decimalPart[index].toString())
            }
            delete(startIndex + maxNumDecimalValues, length)
        }
    }
}

class NumberFormatOutputTransformation(
    private val numberFormat: NumberFormat,
): OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        val originalAmount = originalText.toString()
        if (originalAmount.isEmpty()) return

        // Use String-based format to avoid precision loss from Double conversion
        val formattedAmount = numberFormat.format(originalAmount)

        replace(0, length, formattedAmount)
    }
}

private fun splitNumberParts(
    numberString: String,
    decimalSeparator: Char = '.',
): Pair<String, String?> {
    val parts = numberString.split(decimalSeparator)
    val intPart = parts.getOrNull(0) ?: ""
    val fracPart = parts.getOrNull(1)
    return intPart to fracPart
}

private fun normalizeIntegerPart(intPart: String): String {
    return intPart.trimStart('0').ifEmpty { "0" }
}

private fun applyGroupingSeparator(
    intPart: String,
    groupingSeparator: Char,
    groupingChunk: Int,
): String {
    if (intPart.isEmpty() || groupingChunk <= 0) {
        return intPart
    }

    return intPart.reversed()
        .chunked(groupingChunk)
        .joinToString(groupingSeparator.toString())
        .reversed()
}
