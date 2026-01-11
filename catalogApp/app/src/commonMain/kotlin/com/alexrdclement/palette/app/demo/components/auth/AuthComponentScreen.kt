package com.alexrdclement.palette.app.demo.components.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.components.auth.navigation.AuthComponent
import com.alexrdclement.palette.components.layout.Scaffold

@Composable
fun AuthComponentScreen(
    component: AuthComponent,
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
            AuthComponent.AuthButton -> AuthButtonDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
