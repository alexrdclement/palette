package com.alexrdclement.palette.navigation

interface NavKey {
    companion object
    val pathSegment: PathSegment
    val isDialog: Boolean
        get() = false
}
