package com.alexrdclement.palette.theme.semantic.format

import com.alexrdclement.palette.theme.semantic.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.semantic.format.core.PaletteNumberFormatScheme
import com.alexrdclement.palette.theme.semantic.format.core.TextFormatScheme
import com.alexrdclement.palette.theme.semantic.format.core.PaletteTextFormatScheme
import com.alexrdclement.palette.theme.semantic.format.datetime.DateTimeFormats
import com.alexrdclement.palette.theme.semantic.format.datetime.PaletteDateTimeFormats
import com.alexrdclement.palette.theme.semantic.format.money.MoneyFormatScheme
import com.alexrdclement.palette.theme.semantic.format.money.PaletteMoneyFormatScheme

data class Formats(
    val dateTimeFormats: DateTimeFormats,
    val moneyFormats: MoneyFormatScheme,
    val numberFormats: NumberFormatScheme,
    val textFormats: TextFormatScheme,
)

val PaletteFormats = Formats(
    dateTimeFormats = PaletteDateTimeFormats,
    moneyFormats = PaletteMoneyFormatScheme,
    numberFormats = PaletteNumberFormatScheme,
    textFormats = PaletteTextFormatScheme,
)
