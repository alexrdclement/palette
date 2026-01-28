package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

@Composable
fun rememberNavState(
    startRoute: NavKey,
    navGraph: NavGraph,
): NavState {
    val backStack = rememberSaveable(
        navGraph,
        saver = navBackStackSaver(navGraph)
    ) {
        mutableStateListOf(startRoute)
    }

    return remember(backStack, navGraph) {
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
        } else {
            backStack.add(route)
        }
    }

    fun goBack() {
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
