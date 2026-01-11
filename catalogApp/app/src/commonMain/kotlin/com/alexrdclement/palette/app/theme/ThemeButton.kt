package com.alexrdclement.palette.app.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

@Composable
fun ThemeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        style = ButtonStyleToken.Tertiary,
        onClick = onClick,
        modifier = modifier,
    ) {
        Text("THEME", style = PaletteTheme.typography.labelSmall)
    }
}
