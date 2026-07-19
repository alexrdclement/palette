package com.alexrdclement.palette.theme.semantic.dimension

import com.alexrdclement.palette.theme.semantic.spacing.SpacingToken

data class PaddingScheme(
    val default: PaddingValuesTokenSet,
)

val PalettePaddingScheme = PaddingScheme(
    default = PaddingValuesTokenSet(
        start = SpacingToken.Large,
        top = SpacingToken.Small,
        end = SpacingToken.Large,
        bottom = SpacingToken.Small,
    ),
)

fun PaddingScheme.copy(
    token: PaddingValuesToken,
    tokenSet: PaddingValuesTokenSet,
) = this.copy(
    default = if (token == PaddingValuesToken.Default) tokenSet else this.default,
)

fun PaddingScheme.tokenSet(token: PaddingValuesToken): PaddingValuesTokenSet = when (token) {
    PaddingValuesToken.Default -> default
}
