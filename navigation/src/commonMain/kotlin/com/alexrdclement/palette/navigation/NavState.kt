package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

@Composable
fun rememberNavState(
    startRoute: NavKey,
    navGraph: NavGraph,
): NavState {
    val backStack = remember {
        mutableStateListOf(startRoute)
    }

    return remember {
        NavState(
            backStack = backStack,
            navGraph = navGraph,
        )
    }
}

class NavState(
    val backStack: SnapshotStateList<NavKey>,
    val navGraph: NavGraph,
) {
    fun navigate(
        route: NavKey,
        replace: Boolean = false,
    ) {
        if (replace) {
            backStack[backStack.lastIndex] = route
            return
        } else {
            backStack.add(route)
        }
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }
}
