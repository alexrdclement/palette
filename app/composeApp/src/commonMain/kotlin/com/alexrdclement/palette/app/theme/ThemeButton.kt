package com.alexrdclement.palette.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun ThemeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        style = PaletteTheme.styles.core.button.tertiary,
        onClick = onClick,
        modifier = modifier,
    ) {
        Text("Theme", style = PaletteTheme.styles.core.text.labelSmall)
    }
}
