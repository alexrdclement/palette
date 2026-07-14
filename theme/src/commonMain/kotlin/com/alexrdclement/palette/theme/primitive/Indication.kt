package com.alexrdclement.palette.theme.primitive

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.modifiers.ColorInvertIndication
import com.alexrdclement.palette.modifiers.ColorSplitIndication
import com.alexrdclement.palette.modifiers.ColorSplitMode
import com.alexrdclement.palette.modifiers.NoiseColorMode
import com.alexrdclement.palette.modifiers.NoiseIndication
import com.alexrdclement.palette.modifiers.PixelateIndication
import com.alexrdclement.palette.modifiers.WarpIndication

enum class IndicationPrimitiveToken {
    None,
    ColorInvert,
    ColorSplit,
    Noise,
    Pixelate,
    Warp,
}

fun IndicationPrimitiveToken.toIndication(): Indication = when (this) {
    IndicationPrimitiveToken.None -> NoOpIndication
    IndicationPrimitiveToken.ColorInvert -> ColorInvertIndication(
        amount = { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> .5f
                is PressInteraction.Press -> 1f
                else -> 0f
            }
        },
    )
    IndicationPrimitiveToken.ColorSplit -> ColorSplitIndication(
        xAmount = { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> -0.02f
                is PressInteraction.Press -> 0.02f
                else -> 0f
            }
        },
        yAmount = { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> -0.02f
                is PressInteraction.Press -> 0.02f
                else -> 0f
            }
        },
        colorMode = ColorSplitMode.RGB,
    )
    IndicationPrimitiveToken.Noise -> NoiseIndication(
        colorMode = NoiseColorMode.RandomColorFilterBlack,
        amount = { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> 0.25f
                is PressInteraction.Press -> 1f
                else -> 0f
            }
        },
    )
    IndicationPrimitiveToken.Pixelate -> PixelateIndication(
        subdivisions = { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> 2
                is PressInteraction.Press -> 6
                else -> 0
            }
        },
    )
    IndicationPrimitiveToken.Warp -> WarpIndication(
        point = { interaction ->
            when (interaction) {
                is PressInteraction.Press -> interaction.pressPosition
                is PressInteraction.Release -> interaction.press.pressPosition
                is PressInteraction.Cancel -> interaction.press.pressPosition
                else -> Offset.Zero
            }
        },
        radius = { interaction ->
            when (interaction) {
                is PressInteraction.Press -> 100.dp
                else -> 0.dp
            }
        },
        amount = { interaction ->
            when (interaction) {
                is PressInteraction.Press -> 0.5f
                else -> 0f
            }
        },
    )
}

data object NoOpIndication : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return object : Modifier.Node() {}
    }
}
