package com.alexrdclement.palette.theme

import com.alexrdclement.palette.theme.format.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.NumberFormatScheme
import com.alexrdclement.palette.theme.format.PaletteMoneyFormatScheme
import com.alexrdclement.palette.theme.format.PaletteNumberFormatScheme

data class Formats(
    val numberFormats: NumberFormatScheme,
    val moneyFormats: MoneyFormatScheme,
)

val PaletteFormats = Formats(
    numberFormats = PaletteNumberFormatScheme,
    moneyFormats = PaletteMoneyFormatScheme,
)
