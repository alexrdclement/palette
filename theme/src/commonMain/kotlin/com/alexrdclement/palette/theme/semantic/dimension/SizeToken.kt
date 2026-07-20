package com.alexrdclement.palette.theme.semantic.dimension

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.theme.PaletteTheme

enum class SizeToken {
    TouchTargetMin,
    IconSmall,
}

fun SizeToken.toSize(size: Size): Dp = when (this) {
    SizeToken.TouchTargetMin -> size.touchTargetMin
    SizeToken.IconSmall -> size.iconSmall
}

@Composable
fun SizeToken.toSize(): Dp = toSize(PaletteTheme.semantic.dimension.size)
