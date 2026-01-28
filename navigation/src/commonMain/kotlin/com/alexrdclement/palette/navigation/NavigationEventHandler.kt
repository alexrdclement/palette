package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun NavigationEventHandler(
    navState: NavState,
    navController: NavController,
)
