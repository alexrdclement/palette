package com.alexrdclement.palette.navigation

import kotlin.jvm.JvmInline

@JvmInline
value class PathSegment(val value: String) {
    companion object {
        val Wildcard = PathSegment("*")
    }

    override fun toString(): String {
        return value
    }
}

fun String.toPathSegment() = PathSegment(
    value = this.replace(Regex("([a-z])([A-Z])"), "$1-$2").lowercase(),
)
