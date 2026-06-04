package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.alexrdclement.palette.components.LocalContentColor

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
