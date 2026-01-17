package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.ContinentalMDY
import com.alexrdclement.palette.formats.datetime.ContinentalYMD
import com.alexrdclement.palette.formats.datetime.MDYContinental
import com.alexrdclement.palette.formats.datetime.MDYContinentalShort
import com.alexrdclement.palette.formats.datetime.YMDContinental
import com.alexrdclement.palette.formats.datetime.YMDContinentalShort
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DateTimeFormat

enum class InstantFormatToken {
    MDYContinental,
    MDYContinentalShort,
    YMDContinental,
    YMDContinentalShort,
    ContinentalMDY,
    ContinentalYMD,
}

fun InstantFormatToken.toDateTimeFormat(): DateTimeFormat<LocalDateTime> = when (this) {
    InstantFormatToken.MDYContinental -> LocalDateTime.Formats.MDYContinental
    InstantFormatToken.MDYContinentalShort -> LocalDateTime.Formats.MDYContinentalShort
    InstantFormatToken.YMDContinental -> LocalDateTime.Formats.YMDContinental
    InstantFormatToken.YMDContinentalShort -> LocalDateTime.Formats.YMDContinentalShort
    InstantFormatToken.ContinentalMDY -> LocalDateTime.Formats.ContinentalMDY
    InstantFormatToken.ContinentalYMD -> LocalDateTime.Formats.ContinentalYMD
}

fun InstantFormatToken.toFormat(): DateTimeFormat<LocalDateTime> = toDateTimeFormat()
