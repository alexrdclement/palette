package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.TimeFormatValue

data class TimeFormatScheme(
    val default: TimeFormatValue,
    val long: TimeFormatValue,
    val short: TimeFormatValue,
)

val PaletteTimeFormatScheme = TimeFormatScheme(
    default = TimeFormatValue.HMAmPmPadZero,
    long = TimeFormatValue.HMSAmPmPadZero,
    short = TimeFormatValue.HMAmPmPadZero,
)
