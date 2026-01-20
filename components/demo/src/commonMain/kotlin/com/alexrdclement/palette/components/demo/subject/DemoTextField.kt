package com.alexrdclement.palette.components.demo.subject

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.TextField
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.TextStyle

@Composable
fun DemoTextField(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = PaletteTheme.styles.text.labelLarge,
) {
    Box(modifier = modifier.fillMaxSize()) {
        TextField(
            state = rememberTextFieldState(),
            textStyle = textStyle,
            modifier = modifier.align(Alignment.Center)
        )
    }
}
