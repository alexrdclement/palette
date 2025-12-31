package com.alexrdclement.palette.theme.util

import kotlin.math.PI

// TODO: share with components
fun Int.toRadians(): Double {
    return this * (PI / 180.0)
}
