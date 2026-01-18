package com.alexrdclement.palette.theme.format.datetime

import com.alexrdclement.palette.formats.datetime.HMAmPmPadZero
import com.alexrdclement.palette.formats.datetime.HMContinental
import com.alexrdclement.palette.formats.datetime.HMSAmPmPadZero
import com.alexrdclement.palette.formats.datetime.HMSContinental
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.DateTimeFormat

enum class TimeFormatToken {
    HMContinental,
    HMAmPmPadZero,
    HMSContinental,
    HMSAmPmPadZero,
}

fun TimeFormatToken.toFormat(): DateTimeFormat<LocalTime> = when (this) {
    TimeFormatToken.HMContinental -> LocalTime.Formats.HMContinental
    TimeFormatToken.HMAmPmPadZero -> LocalTime.Formats.HMAmPmPadZero
    TimeFormatToken.HMSContinental -> LocalTime.Formats.HMSContinental
    TimeFormatToken.HMSAmPmPadZero -> LocalTime.Formats.HMSAmPmPadZero
}
