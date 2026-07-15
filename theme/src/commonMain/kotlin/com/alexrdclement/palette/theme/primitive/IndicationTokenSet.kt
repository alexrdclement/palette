package com.alexrdclement.palette.theme.primitive

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.modifiers.ColorInvertIndication
import com.alexrdclement.palette.modifiers.ColorSplitIndication
import com.alexrdclement.palette.modifiers.ColorSplitMode
import com.alexrdclement.palette.modifiers.NoiseColorMode
import com.alexrdclement.palette.modifiers.NoiseIndication
import com.alexrdclement.palette.modifiers.PixelateIndication
import com.alexrdclement.palette.modifiers.WarpIndication

sealed interface IndicationTokenSet {
    fun toIndication(): Indication

    data object None : IndicationTokenSet {
        override fun toIndication(): Indication = NoOpIndication
    }

    data class ColorInvert(
        val amount: Float = 1f,
    ) : IndicationTokenSet {
        override fun toIndication(): Indication = ColorInvertIndication(
            amount = { interaction ->
                when (interaction) {
                    is HoverInteraction.Enter -> amount / 2f
                    is PressInteraction.Press -> amount
                    else -> 0f
                }
            },
        )
    }

    data class ColorSplit(
        val amount: Float = 0.02f,
        val colorMode: ColorSplitMode = ColorSplitMode.RGB,
    ) : IndicationTokenSet {
        override fun toIndication(): Indication = ColorSplitIndication(
            xAmount = { interaction ->
                when (interaction) {
                    is HoverInteraction.Enter -> -amount
                    is PressInteraction.Press -> amount
                    else -> 0f
                }
            },
            yAmount = { interaction ->
                when (interaction) {
                    is HoverInteraction.Enter -> -amount
                    is PressInteraction.Press -> amount
                    else -> 0f
                }
            },
            colorMode = colorMode,
        )
    }

    data class Noise(
        val amount: Float = 1f,
        val colorMode: NoiseColorMode = NoiseColorMode.RandomColorFilterBlack,
    ) : IndicationTokenSet {
        override fun toIndication(): Indication = NoiseIndication(
            colorMode = colorMode,
            amount = { interaction ->
                when (interaction) {
                    is HoverInteraction.Enter -> amount / 4f
                    is PressInteraction.Press -> amount
                    else -> 0f
                }
            },
        )
    }

    data class Pixelate(
        val subdivisions: Int = 6,
    ) : IndicationTokenSet {
        override fun toIndication(): Indication = PixelateIndication(
            subdivisions = { interaction ->
                when (interaction) {
                    is HoverInteraction.Enter -> subdivisions / 3
                    is PressInteraction.Press -> subdivisions
                    else -> 0
                }
            },
        )
    }

    data class Warp(
        val amount: Float = 0.5f,
        val radius: Dp = 100.dp,
    ) : IndicationTokenSet {
        override fun toIndication(): Indication = WarpIndication(
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
                    is PressInteraction.Press -> radius
                    else -> 0.dp
                }
            },
            amount = { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> amount
                    else -> 0f
                }
            },
        )
    }
}
