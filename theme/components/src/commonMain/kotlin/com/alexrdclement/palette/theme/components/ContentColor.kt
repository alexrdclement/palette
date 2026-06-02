package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.theme.ColorScheme
import com.alexrdclement.palette.theme.PaletteTheme

fun ColorScheme.contentColorFor(backgroundColor: Color): Color =
    when (backgroundColor) {
        primary -> onPrimary
        secondary -> onSecondary
        background -> onBackground
        surface -> onSurface
        else -> Color.Unspecified
    }

@Composable
fun contentColorFor(backgroundColor: Color) =
    PaletteTheme.colorScheme.contentColorFor(backgroundColor).takeOrElse {
        LocalContentColor.current
    }
