package com.alexrdclement.palette.theme.format

import com.alexrdclement.palette.theme.format.datetime.DateTimeFormats
import com.alexrdclement.palette.theme.format.money.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.format.datetime.PaletteDateTimeFormats
import com.alexrdclement.palette.theme.format.money.PaletteMoneyFormatScheme
import com.alexrdclement.palette.theme.format.core.PaletteNumberFormatScheme

data class Formats(
    val dateTimeFormats: DateTimeFormats,
    val moneyFormats: MoneyFormatScheme,
    val numberFormats: NumberFormatScheme,
)

fun Formats.update(
    dateTimeFormats: DateTimeFormats? = null,
    moneyFormats: MoneyFormatScheme? = null,
    numberFormats: NumberFormatScheme? = null,
) = this.copy(
    dateTimeFormats = dateTimeFormats ?: this.dateTimeFormats,
    moneyFormats = moneyFormats ?: this.moneyFormats,
    numberFormats = numberFormats ?: this.numberFormats,
)

val PaletteFormats = Formats(
    dateTimeFormats = PaletteDateTimeFormats,
    moneyFormats = PaletteMoneyFormatScheme,
    numberFormats = PaletteNumberFormatScheme,
)
