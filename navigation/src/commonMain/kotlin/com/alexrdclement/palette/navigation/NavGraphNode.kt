package com.alexrdclement.palette.navigation

import kotlin.reflect.KClass

data class NavGraphNode(
    val pathSegment: PathSegment,
    val navKeyClass: KClass<out NavKey>,
    val parser: (PathSegment) -> NavKey?,
    val parent: NavKey?,
    val children: List<NavGraphNode>,
    val isGraphRoot: Boolean = false,
)
