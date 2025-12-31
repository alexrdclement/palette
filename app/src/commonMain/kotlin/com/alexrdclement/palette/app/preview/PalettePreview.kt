package com.alexrdclement.palette.app.preview

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun PalettePreview(
    content: @Composable () -> Unit,
) {
    PaletteTheme {
        Surface(
            content = content,
        )
    }
}
