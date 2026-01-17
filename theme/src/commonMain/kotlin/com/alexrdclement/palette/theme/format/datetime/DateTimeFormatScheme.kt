package com.alexrdclement.palette.theme.format.datetime

data class DateTimeFormatScheme(
    val dateFormat: DateFormatToken,
    val timeFormat: TimeFormatToken,
    val dateTimeFormat: DateTimeFormatToken,
    val instantFormat: InstantFormatToken,
)

fun DateTimeFormatScheme.copy(
    dateFormatToken: DateFormatToken? = null,
    timeFormatToken: TimeFormatToken? = null,
    dateTimeFormatToken: DateTimeFormatToken? = null,
    instantFormatToken: InstantFormatToken? = null,
) = this.copy(
    dateFormat = dateFormatToken ?: this.dateFormat,
    timeFormat = timeFormatToken ?: this.timeFormat,
    dateTimeFormat = dateTimeFormatToken ?: this.dateTimeFormat,
    instantFormat = instantFormatToken ?: this.instantFormat,
)

val PaletteDateTimeFormatScheme = DateTimeFormatScheme(
    dateFormat = DateFormatToken.YMD,
    timeFormat = TimeFormatToken.HMContinental,
    dateTimeFormat = DateTimeFormatToken.YMDContinental,
    instantFormat = InstantFormatToken.YMDContinental,
)
