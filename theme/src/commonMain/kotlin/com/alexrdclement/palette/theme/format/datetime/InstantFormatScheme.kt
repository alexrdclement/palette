package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.InstantFormatValue

data class InstantFormatScheme(
    val default: InstantFormatValue,
    val long: InstantFormatValue,
    val short: InstantFormatValue,
)

val PaletteInstantFormatScheme = InstantFormatScheme(
    default = InstantFormatValue.MDYContinentalShort,
    long = InstantFormatValue.MDYContinental,
    short = InstantFormatValue.MDYContinentalShort,
)
