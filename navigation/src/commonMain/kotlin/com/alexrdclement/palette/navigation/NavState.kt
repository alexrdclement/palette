package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

@Composable
fun rememberNavState(
    navGraph: NavGraph,
    deeplink: String? = null,
    onWouldBecomeEmpty: () -> Unit = {},
): NavState {
    val startRoute = navGraph.startRoute
    val backStack = rememberSaveable(
        navGraph,
        saver = navBackStackSaver(navGraph)
    ) {
        if (deeplink == null) return@rememberSaveable mutableStateListOf(startRoute)

        val route = navGraph.parseDeeplink(deeplink)
        if (route != null) {
            mutableStateListOf(route)
        } else {
            mutableStateListOf(startRoute)
        }
    }
    val currentOnWouldBecomeEmpty by rememberUpdatedState(onWouldBecomeEmpty)

    return remember(backStack, navGraph) {
        NavState(
            backStack = backStack,
            navGraph = navGraph,
            onWouldBecomeEmpty = currentOnWouldBecomeEmpty,
        )
    }
}

class NavState(
    val backStack: SnapshotStateList<NavKey>,
    val navGraph: NavGraph,
    val onWouldBecomeEmpty: () -> Unit = {},
) {
    val currentRoute: NavKey?
        get() = backStack.lastOrNull()

    val previousRoute: NavKey?
        get() = backStack.getOrNull(backStack.size - 2)

    fun navigate(
        route: NavKey,
        replace: Boolean = false,
    ) {
        if (replace) {
            backStack[backStack.lastIndex] = route
        } else {
            backStack.add(route)
        }
    }

    fun goBack() {
        if (backStack.size <= 1) {
            onWouldBecomeEmpty()
            return
        }
        backStack.removeLastOrNull()
    }
}

private fun navBackStackSaver(navGraph: NavGraph): Saver<SnapshotStateList<NavKey>, String> = Saver(
    save = { backStack ->
        backStack.joinToString(separator = "|") { route ->
            route.toDeeplink(navGraph)
        }
    },
    restore = { saved ->
        saved.split("|")
            .mapNotNull { deeplink ->
                NavKey.fromDeeplink(deeplink, navGraph)
            }
            .toMutableStateList()
    }
)
