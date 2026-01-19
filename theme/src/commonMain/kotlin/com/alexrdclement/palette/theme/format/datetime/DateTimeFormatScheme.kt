package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.DateTimeFormatValue

data class DateTimeFormatScheme(
    val default: DateTimeFormatValue,
    val long: DateTimeFormatValue,
    val short: DateTimeFormatValue,
)

val PaletteDateTimeFormatScheme = DateTimeFormatScheme(
    default = DateTimeFormatValue.MDYContinentalShort,
    long = DateTimeFormatValue.MDYContinental,
    short = DateTimeFormatValue.MDYContinentalShort,
)
