package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.MDY
import com.alexrdclement.palette.formats.datetime.MDYLong
import com.alexrdclement.palette.formats.datetime.MDYShort
import com.alexrdclement.palette.formats.datetime.YMD
import com.alexrdclement.palette.formats.datetime.YMDShort
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat

enum class DateFormatToken {
    YMD,
    YMDShort,
    MDY,
    MDYShort,
    MDYLong,
}

fun DateFormatToken.toFormat(): DateTimeFormat<LocalDate> = when (this) {
    DateFormatToken.YMD -> LocalDate.Formats.YMD
    DateFormatToken.YMDShort -> LocalDate.Formats.YMDShort
    DateFormatToken.MDY -> LocalDate.Formats.MDY
    DateFormatToken.MDYShort -> LocalDate.Formats.MDYShort
    DateFormatToken.MDYLong -> LocalDate.Formats.MDYLong
}
