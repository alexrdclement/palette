package com.alexrdclement.palette.components.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.theme.PaletteTheme

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
