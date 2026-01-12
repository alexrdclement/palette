package com.alexrdclement.palette.components.format

import com.alexrdclement.palette.theme.format.NumberFormat
import kotlin.test.Test
import kotlin.test.assertEquals

class NumberFormatTest {

    private val testFormat = NumberFormat(
        numDecimalValuesRange = 0..2,
        positiveSign = null,
        negativeSign = "-",
        decimalSeparator = '.',
        groupingSeparator = ',',
        groupingChunk = 3,
    )

    @Test
    fun basicFormatting() {
        val format = testFormat

        val testCases = listOf(
            0.0 to "0",
            1.0 to "1",
            10.0 to "10",
            100.0 to "100",
            1000.0 to "1,000",
            10000.0 to "10,000",
            100000.0 to "100,000",
            1000000.0 to "1,000,000",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }

    @Test
    fun decimalFormatting() {
        val format = testFormat

        val testCases = listOf(
            0.5 to "0.5",
            1.5 to "1.5",
            10.25 to "10.25",
            100.99 to "100.99",
            1000.01 to "1,000.01",
            1234.56 to "1,234.56",
            0.1 to "0.1",
            0.12 to "0.12",
            0.001 to "0",
            0.01 to "0.01",
            0.99 to "0.99",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }

    @Test
    fun positiveSign() {
        val format = testFormat.copy(
            positiveSign = "+",
        )

        val testCases = listOf(
            1.0 to "+1",
            100.0 to "+100",
            1000.0 to "+1,000",
            1234.56 to "+1,234.56",
            0.0 to "0",
            -1.0 to "-1",
            -100.0 to "-100",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }

    @Test
    fun negativeSign() {
        val format = testFormat.copy(
            negativeSign = "!",
        )

        val testCases = listOf(
            -1.0 to "!1",
            -10.0 to "!10",
            -100.0 to "!100",
            -1000.0 to "!1,000",
            -1234.56 to "!1,234.56",
            -0.5 to "!0.5",
            -0.99 to "!0.99",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }

    @Test
    fun customDecimalRange() {
        val format = testFormat.copy(numDecimalValuesRange = 2..4)

        val testCases = listOf(
            0.0 to "0.00",
            1.0 to "1.00",
            1.5 to "1.50",
            1.234 to "1.234",
            1.2345 to "1.2345",
            1.23456 to "1.2345",
            100.99 to "100.99",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }

    @Test
    fun customSeparators() {
        val format = testFormat.copy(
            decimalSeparator = ',',
            groupingSeparator = ' ',
        )

        val testCases = listOf(
            1000.0 to "1 000",
            1234.56 to "1 234,56",
            10000.99 to "10 000,99",
            0.5 to "0,5",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }

    @Test
    fun customGroupingChunk() {
        val format = testFormat.copy(
            groupingChunk = 2,
        )

        val testCases = listOf(
            100.0 to "1,00",
            1000.0 to "10,00",
            10000.0 to "1,00,00",
            123456.78 to "12,34,56.78",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }

    @Test
    fun noGroupingChunk() {
        val format = testFormat.copy(
            groupingChunk = 0,
        )

        val testCases = listOf(
            1000.0 to "1000",
            10000.0 to "10000",
            1000000.0 to "1000000",
            1234.56 to "1234.56",
        )

        testCases.forEach { (amount, expected) ->
            assertEquals(expected, format.format(amount), "Failed for amount: $amount")
        }
    }
}
