package com.alexrdclement.palette.formats.core

import androidx.annotation.CheckResult
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.delete
import androidx.compose.ui.text.input.KeyboardType
import kotlin.math.abs
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
    val decimalSeparatorStr = decimalSeparator.toString()
    val groupingSeparatorStr = groupingSeparator.toString()

    val sign = when {
        amount < 0 -> negativeSign
        amount > 0 && positiveSign != null -> positiveSign
        else -> ""
    }
    val absAmount = abs(amount)

    val intPartValue = absAmount.toLong()
    val fracPartValue = absAmount - intPartValue

    val intPart = intPartValue.toString()
    val decimalPart = if (fracPartValue > 0 || minNumDecimalValues > 0) {
        var remaining = fracPartValue
        val rawDecimal = buildString {
            for (i in 0 until maxNumDecimalValues) {
                remaining *= 10
                // Extract digit (floor), adding small epsilon to handle floating point errors
                val digit = (remaining + 0.00001).toInt() % 10
                append(digit)
                remaining -= remaining.toInt()
                if (remaining < 1e-10) break
            }
        }

        // Trim trailing zeros but keep at least minNumDecimalValues
        val trimmed = rawDecimal.trimEnd('0')
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

    // Grouping separator every groupingChunk digits, right to left
    val formattedIntPart = if (intPart.isNotEmpty() && groupingChunk > 0) {
        intPart.reversed()
            .chunked(groupingChunk)
            .joinToString(groupingSeparatorStr)
            .reversed()
    } else {
        intPart
    }

    return if (decimalPart != null) {
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
    private val decimalSeparatorStr = decimalSeparator.toString()

    override val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Decimal,
    )

    override fun TextFieldBuffer.transformInput() {
        filterChars()
        filterConsecutiveDecimals()

        val parts = asCharSequence().split(decimalSeparatorStr)
        val intPart = parts.firstOrNull()?.filter { it.isDigit() } ?: ""
        val decimalPart = parts.getOrNull(1)?.filter { it.isDigit() }

        if (parts.size > 2) {
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
            val indexOfFirstNonZero = mutableIntPart.indexOfFirst { it != '0' }
            val newIntPart = if (indexOfFirstNonZero > 0) {
                mutableIntPart.substring(indexOfFirstNonZero)
            } else {
                "0"
            }
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

        val amountDouble = originalAmount.toDoubleOrNull() ?: return
        val formattedAmount = numberFormat.format(amountDouble)

        replace(0, length, formattedAmount)
    }
}
