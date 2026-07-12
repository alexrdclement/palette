package com.alexrdclement.palette.theme.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.navigation.BackNavigationButton as BaseBackNavigationButton

@Composable
fun BackNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: BackNavigationButtonStyle = PaletteTheme.component.navigation.backNavigationButton,
) = BaseBackNavigationButton(
    onClick = onClick,
    modifier = modifier,
    style = style,
)
