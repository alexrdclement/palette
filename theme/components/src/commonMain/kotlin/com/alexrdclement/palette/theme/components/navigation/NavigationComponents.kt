package com.alexrdclement.palette.theme.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.navigation.BackNavigationButton as BaseBackNavigationButton

/** [BaseBackNavigationButton] pre-bound to the palette theme (`style` defaults to `styles.navigation.backNavigationButton`). */
@Composable
fun BackNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: BackNavigationButtonStyle = PaletteTheme.styles.navigation.backNavigationButton,
) = BaseBackNavigationButton(
    onClick = onClick,
    modifier = modifier,
    style = style,
)
