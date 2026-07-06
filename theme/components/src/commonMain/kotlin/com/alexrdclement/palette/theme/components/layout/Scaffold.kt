package com.alexrdclement.palette.theme.components.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.layout.ScaffoldStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.layout.Scaffold as BaseScaffold

@Composable
fun Scaffold(
    topBar: @Composable () -> Unit = {},
    floatingAction: @Composable () -> Unit = {},
    navigationBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    style: ScaffoldStyle = PaletteTheme.styles.layout.scaffold,
    content: @Composable (PaddingValues) -> Unit,
) = BaseScaffold(
    topBar = topBar,
    floatingAction = floatingAction,
    navigationBar = navigationBar,
    modifier = modifier,
    style = style,
    content = content,
)
