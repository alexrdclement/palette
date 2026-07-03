package com.alexrdclement.palette.theme.components.core

import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.border
import com.alexrdclement.palette.theme.styles.BorderStyleToken
import com.alexrdclement.palette.theme.styles.BorderStyleTokenSet
import com.alexrdclement.palette.theme.styles.resolve
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toComposeShape

@Composable
fun Modifier.border(
    style: BorderStyleToken,
): Modifier {
    return this.border(style = style.resolve())
}

@Composable
fun Modifier.border(
    style: BorderStyleTokenSet,
): Modifier = this.border(
    width = style.width,
    color = style.color.toColor(),
    shape = style.shape.toComposeShape(),
)
