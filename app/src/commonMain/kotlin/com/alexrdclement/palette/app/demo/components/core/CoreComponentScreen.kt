package com.alexrdclement.palette.app.demo.components.core

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.components.core.navigation.CoreComponent
import com.alexrdclement.palette.components.layout.Scaffold

@Composable
fun CoreComponentScreen(
    component: CoreComponent,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = component.title,
                onNavigateBack = onNavigateBack,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (component) {
            CoreComponent.Button -> ButtonDemo(
                modifier = Modifier.padding(innerPadding)
            )
            CoreComponent.Text -> TextDemo(
                modifier = Modifier.padding(innerPadding)
            )
            CoreComponent.TextField -> TextFieldDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
