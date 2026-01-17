package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.ContinentalMDY
import com.alexrdclement.palette.formats.datetime.ContinentalYMD
import com.alexrdclement.palette.formats.datetime.MDYContinental
import com.alexrdclement.palette.formats.datetime.MDYContinentalShort
import com.alexrdclement.palette.formats.datetime.YMDContinental
import com.alexrdclement.palette.formats.datetime.YMDContinentalShort
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DateTimeFormat

enum class DateTimeFormatToken {
    MDYContinental,
    MDYContinentalShort,
    YMDContinental,
    YMDContinentalShort,
    ContinentalMDY,
    ContinentalYMD,
}

fun DateTimeFormatToken.toDateTimeFormat(): DateTimeFormat<LocalDateTime> = when (this) {
    DateTimeFormatToken.MDYContinental -> LocalDateTime.Formats.MDYContinental
    DateTimeFormatToken.MDYContinentalShort -> LocalDateTime.Formats.MDYContinentalShort
    DateTimeFormatToken.YMDContinental -> LocalDateTime.Formats.YMDContinental
    DateTimeFormatToken.YMDContinentalShort -> LocalDateTime.Formats.YMDContinentalShort
    DateTimeFormatToken.ContinentalMDY -> LocalDateTime.Formats.ContinentalMDY
    DateTimeFormatToken.ContinentalYMD -> LocalDateTime.Formats.ContinentalYMD
}

fun DateTimeFormatToken.toFormat(): DateTimeFormat<LocalDateTime> = toDateTimeFormat()
