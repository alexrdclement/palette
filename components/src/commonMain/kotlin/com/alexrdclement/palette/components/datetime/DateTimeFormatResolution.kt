package com.alexrdclement.palette.components.datetime

import com.alexrdclement.palette.theme.format.DateFormatToken
import com.alexrdclement.palette.theme.format.DateTimeFormatToken
import com.alexrdclement.palette.theme.format.InstantFormatToken
import com.alexrdclement.palette.theme.format.TimeFormatToken
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat

// TODO token-system: move to theme or elsewhere

fun DateFormatToken.toDateTimeFormat(): DateTimeFormat<LocalDate> = when (this) {
    DateFormatToken.YMD -> LocalDateFormatYMD
    DateFormatToken.YMDShort -> LocalDateFormatYMDShort
    DateFormatToken.MDY -> LocalDateFormatMDY
    DateFormatToken.MDYShort -> LocalDateFormatMDYShort
    DateFormatToken.MDYLong -> LocalDateFormatMDYLong
}

fun DateFormatToken.toFormat(): DateTimeFormat<LocalDate> = toDateTimeFormat()

fun TimeFormatToken.toDateTimeFormat(): DateTimeFormat<LocalTime> = when (this) {
    TimeFormatToken.HMContinental -> LocalTimeFormatHMContinental
    TimeFormatToken.HMAmPmPadZero -> LocalTimeFormatHMAmPmPadZero
    TimeFormatToken.HMSContinental -> LocalTimeFormatHMSContinental
    TimeFormatToken.HMSAmPmPadZero -> LocalTimeFormatHMSAmPmPadZero
}

fun TimeFormatToken.toFormat(): DateTimeFormat<LocalTime> = toDateTimeFormat()

fun DateTimeFormatToken.toDateTimeFormat(): DateTimeFormat<LocalDateTime> = when (this) {
    DateTimeFormatToken.MDYContinental -> LocalDateTimeFormatMDYContinental
    DateTimeFormatToken.MDYContinentalShort -> LocalDateTimeFormatMDYContinentalShort
    DateTimeFormatToken.YMDContinental -> LocalDateTimeFormatYMDContinental
    DateTimeFormatToken.YMDContinentalShort -> LocalDateTimeFormatYMDContinentalShort
    DateTimeFormatToken.ContinentalMDY -> LocalDateTimeFormatContinentalMDY
    DateTimeFormatToken.ContinentalYMD -> LocalDateTimeFormatContinentalYMD
}

fun DateTimeFormatToken.toFormat(): DateTimeFormat<LocalDateTime> = toDateTimeFormat()

fun InstantFormatToken.toDateTimeFormat(): DateTimeFormat<LocalDateTime> = when (this) {
    InstantFormatToken.MDYContinental -> LocalDateTime.Formats.MDYContinental
    InstantFormatToken.MDYContinentalShort -> LocalDateTime.Formats.MDYContinentalShort
    InstantFormatToken.YMDContinental -> LocalDateTime.Formats.YMDContinental
    InstantFormatToken.YMDContinentalShort -> LocalDateTime.Formats.YMDContinentalShort
    InstantFormatToken.ContinentalMDY -> LocalDateTime.Formats.ContinentalMDY
    InstantFormatToken.ContinentalYMD -> LocalDateTime.Formats.ContinentalYMD
}

fun InstantFormatToken.toFormat(): DateTimeFormat<LocalDateTime> = toDateTimeFormat()
