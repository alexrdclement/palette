package com.alexrdclement.palette.theme.semantic.dimensions

import com.alexrdclement.palette.theme.semantic.spacing.SpacingToken

data class PaddingScheme(
    val default: PaddingValueTokenSet,
    val compact: PaddingValueTokenSet,
)

val PalettePaddingScheme = PaddingScheme(
    default = PaddingValueTokenSet(
        start = SpacingToken.Large,
        top = SpacingToken.Small,
        end = SpacingToken.Large,
        bottom = SpacingToken.Small,
    ),
    compact = PaddingValueTokenSet(all = SpacingToken.Medium),
)

fun PaddingScheme.copy(
    token: PaddingValueToken,
    tokenSet: PaddingValueTokenSet,
) = this.copy(
    default = if (token == PaddingValueToken.Default) tokenSet else this.default,
    compact = if (token == PaddingValueToken.Compact) tokenSet else this.compact,
)

fun PaddingScheme.tokenSet(token: PaddingValueToken): PaddingValueTokenSet = when (token) {
    PaddingValueToken.Default -> default
    PaddingValueToken.Compact -> compact
}
