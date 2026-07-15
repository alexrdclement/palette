package com.alexrdclement.palette.theme.semantic.interaction

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.primitive.IndicationPrimitiveToken
import com.alexrdclement.palette.theme.primitive.IndicationTokenSet

enum class IndicationToken {
    Default,
}

fun IndicationToken.primitiveToken(interactionScheme: InteractionScheme): IndicationPrimitiveToken {
    return when (this) {
        IndicationToken.Default -> interactionScheme.default
    }
}

fun IndicationToken.toIndication(
    interactionScheme: InteractionScheme,
    indicationPrimitives: Map<IndicationPrimitiveToken, IndicationTokenSet>,
): Indication {
    return indicationPrimitives.getValue(primitiveToken(interactionScheme)).toIndication()
}

@Composable
fun IndicationToken.toIndication(): Indication {
    return toIndication(PaletteTheme.semantic.interaction, PaletteTheme.primitive.indication)
}
