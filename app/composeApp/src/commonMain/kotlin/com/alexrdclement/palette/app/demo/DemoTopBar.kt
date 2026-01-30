package com.alexrdclement.palette.app.demo

import androidx.compose.foundation.basicMarquee
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.layout.TopBar
import com.alexrdclement.palette.components.navigation.BackNavigationButton
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun DemoTopBar(
    title: String,
    onNavigateUp: () -> Unit,
    onThemeClick: () -> Unit,
    navButton: @Composable () -> Unit = {
        BackNavigationButton(onNavigateUp)
    },
    actions: @Composable () -> Unit = {
        ThemeButton(onClick = onThemeClick)
    }
) {
    TopBar(
        title = {
            Text(
                text = title,
                style = PaletteTheme.styles.text.titleMedium,
                modifier = Modifier.basicMarquee(),
            )
        },
        navButton = navButton,
        actions = actions,
    )
}
