package com.alexrdclement.palette.theme.component

import androidx.compose.runtime.staticCompositionLocalOf
import com.alexrdclement.palette.theme.component.core.BorderStyleToken
import com.alexrdclement.palette.theme.component.core.BorderStyleTokenSet
import com.alexrdclement.palette.theme.component.core.ButtonStyleToken
import com.alexrdclement.palette.theme.component.core.ButtonStyleTokenSet
import com.alexrdclement.palette.theme.component.core.SurfaceStyleToken
import com.alexrdclement.palette.theme.component.core.SurfaceStyleTokenSet
import com.alexrdclement.palette.theme.component.core.TextStyleToken
import com.alexrdclement.palette.theme.component.core.TextStyleTokenSet

/**
 * Component-tier token inputs for [com.alexrdclement.palette.theme.PaletteTheme]: the editable token
 * set for every component style token, seeded from each token's `default`. Edited through
 * [com.alexrdclement.palette.theme.control.ThemeController.updateComponent] and provided via
 * [LocalComponentTokens]; surfaced as resolved styles through
 * [com.alexrdclement.palette.theme.PaletteTheme.component].
 *
 * The maps are complete (every token present), so resolution reads them with [Map.getValue].
 */
data class ComponentTokens(
    val text: Map<TextStyleToken, TextStyleTokenSet> =
        TextStyleToken.entries.associateWith { it.default },
    val button: Map<ButtonStyleToken, ButtonStyleTokenSet> =
        ButtonStyleToken.entries.associateWith { it.default },
    val surface: Map<SurfaceStyleToken, SurfaceStyleTokenSet> =
        SurfaceStyleToken.entries.associateWith { it.default },
    val border: Map<BorderStyleToken, BorderStyleTokenSet> =
        BorderStyleToken.entries.associateWith { it.default },
)

val LocalComponentTokens = staticCompositionLocalOf { ComponentTokens() }
