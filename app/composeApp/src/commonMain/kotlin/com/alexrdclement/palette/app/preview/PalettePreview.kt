package com.alexrdclement.palette.app.preview

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.components.Surface
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun PalettePreview(
    content: @Composable (PaddingValues) -> Unit,
) {
    PaletteTheme {
        Surface(
            content = content,
        )
    }
}
