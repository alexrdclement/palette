package com.alexrdclement.palette.theme

import com.alexrdclement.palette.theme.format.DateTimeFormatScheme
import com.alexrdclement.palette.theme.format.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.NumberFormatScheme
import com.alexrdclement.palette.theme.format.PaletteDateTimeFormatScheme
import com.alexrdclement.palette.theme.format.PaletteMoneyFormatScheme
import com.alexrdclement.palette.theme.format.PaletteNumberFormatScheme

data class Formats(
    val dateTimeFormats: DateTimeFormatScheme,
    val moneyFormats: MoneyFormatScheme,
    val numberFormats: NumberFormatScheme,
)

val PaletteFormats = Formats(
    dateTimeFormats = PaletteDateTimeFormatScheme,
    moneyFormats = PaletteMoneyFormatScheme,
    numberFormats = PaletteNumberFormatScheme,
)
