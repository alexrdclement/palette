package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState

@Composable
actual fun NavigationEventHandler(
    navState: NavState,
    navController: NavController,
) {
    // No-op
}
