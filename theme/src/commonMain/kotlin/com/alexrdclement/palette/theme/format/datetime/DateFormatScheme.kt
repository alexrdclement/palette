package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.DateFormatValue

data class DateFormatScheme(
    val default: DateFormatValue,
    val long: DateFormatValue,
    val short: DateFormatValue,
)

val PaletteDateFormatScheme = DateFormatScheme(
    default = DateFormatValue.MDY,
    long = DateFormatValue.MDYLong,
    short = DateFormatValue.MDYShort,
)
