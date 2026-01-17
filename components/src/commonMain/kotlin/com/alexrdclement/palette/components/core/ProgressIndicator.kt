package com.alexrdclement.palette.components.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IndeterminateProgressIndicator(
    modifier: Modifier = Modifier,
) {
    Text("...", modifier = modifier)
}

@Preview
@Composable
fun PreviewIndeterminateProgressIndicator() {
    PaletteTheme {
        IndeterminateProgressIndicator()
    }
}
