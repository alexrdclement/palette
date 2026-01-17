package com.alexrdclement.palette.theme.format

import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatScheme
import com.alexrdclement.palette.theme.format.money.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.format.datetime.PaletteDateTimeFormatScheme
import com.alexrdclement.palette.theme.format.money.PaletteMoneyFormatScheme
import com.alexrdclement.palette.theme.format.core.PaletteNumberFormatScheme

data class Formats(
    val dateTimeFormats: DateTimeFormatScheme,
    val moneyFormats: MoneyFormatScheme,
    val numberFormats: NumberFormatScheme,
)

fun Formats.update(
    dateTimeFormats: DateTimeFormatScheme? = null,
    moneyFormats: MoneyFormatScheme? = null,
    numberFormats: NumberFormatScheme? = null,
) = this.copy(
    dateTimeFormats = dateTimeFormats ?: this.dateTimeFormats,
    moneyFormats = moneyFormats ?: this.moneyFormats,
    numberFormats = numberFormats ?: this.numberFormats,
)

val PaletteFormats = Formats(
    dateTimeFormats = PaletteDateTimeFormatScheme,
    moneyFormats = PaletteMoneyFormatScheme,
    numberFormats = PaletteNumberFormatScheme,
)
