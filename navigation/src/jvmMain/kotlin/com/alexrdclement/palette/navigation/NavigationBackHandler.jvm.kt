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
    val navEventState = rememberNavigationEventState(
        currentInfo = NavigationEventInfo.None,
    )
    NavigationBackHandler(
        state = navEventState,
        isBackEnabled = navState.backStack.size > 1,
        onBackCompleted = {
            navController.goBack()
        },
    )
}
