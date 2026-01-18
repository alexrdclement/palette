package com.alexrdclement.palette.theme.format

import com.alexrdclement.palette.theme.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.format.core.PaletteNumberFormatScheme
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormats
import com.alexrdclement.palette.theme.format.datetime.PaletteDateTimeFormats
import com.alexrdclement.palette.theme.format.money.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.money.PaletteMoneyFormatScheme

data class Formats(
    val dateTimeFormats: DateTimeFormats,
    val moneyFormats: MoneyFormatScheme,
    val numberFormats: NumberFormatScheme,
)

val PaletteFormats = Formats(
    dateTimeFormats = PaletteDateTimeFormats,
    moneyFormats = PaletteMoneyFormatScheme,
    numberFormats = PaletteNumberFormatScheme,
)
