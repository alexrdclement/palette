package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.IndeterminateProgressIndicator
import com.alexrdclement.palette.theme.PaletteTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun IndeterminateProgressDialogContent(
    title: String = "",
    modifier: Modifier = Modifier,
) {
    DialogContent(
        title = title,
        modifier = modifier,
    ) {
        IndeterminateProgressIndicator(
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun IndeterminateProgressDialogContentPreview() {
    PaletteTheme {
        IndeterminateProgressDialogContent(
            title = "Doing something"
        )
    }
}
