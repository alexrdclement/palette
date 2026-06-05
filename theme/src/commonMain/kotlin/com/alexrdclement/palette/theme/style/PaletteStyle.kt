// StyleScope is a sealed interface in the current CMP release. Inheriting from it in a
// different module requires suppressing both the unresolved CustomStyle reference and the
// sealed-inheritor error. When Foundation 1.12.0-alpha03+ lands in JetBrains CMP, these
// suppressions are removed and the file compiles cleanly.
@file:Suppress(
    "UNRESOLVED_REFERENCE",
    "SEALED_INHERITOR_IN_DIFFERENT_PACKAGE",
    "SEALED_INHERITOR_IN_DIFFERENT_MODULE",
)
// Foundation's experimental opt-in is scoped to this file so PaletteStyle / PaletteStyleScope
// are usable by callers without requiring @OptIn(ExperimentalFoundationStyleApi::class).
@file:OptIn(ExperimentalFoundationStyleApi::class)
package com.alexrdclement.palette.theme.style

import androidx.compose.foundation.Indication
import androidx.compose.foundation.style.CustomStyle
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.StyleScope
import com.alexrdclement.palette.theme.ColorScheme
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.LocalPaletteColorScheme
import com.alexrdclement.palette.theme.LocalPaletteFormats
import com.alexrdclement.palette.theme.LocalPaletteIndication
import com.alexrdclement.palette.theme.LocalPaletteShapes
import com.alexrdclement.palette.theme.LocalPaletteSpacing
import com.alexrdclement.palette.theme.LocalPaletteStyles
import com.alexrdclement.palette.theme.LocalPaletteTypography
import com.alexrdclement.palette.theme.ShapeScheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.Spacing
import com.alexrdclement.palette.theme.SpacingToken
import com.alexrdclement.palette.theme.Styles
import com.alexrdclement.palette.theme.Typography
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.modifiers.toStyle
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toComposeShape
import com.alexrdclement.palette.theme.toComposeTextStyle
import com.alexrdclement.palette.theme.toShape
import com.alexrdclement.palette.theme.toSpacing

// PaletteStyleScope is the receiver for all PaletteStyle { } lambdas.
//
// Every Palette theme object is directly readable via properties, and every Palette token
// type has a shorthand method. Reads use .currentValue (draw/layout phase), not .current
// (Composition), because Style lambdas execute outside of Composition.
//
// Limitation: StyleScope.disabled { } has StyleScope as its inner receiver, not
// PaletteStyleScope, so token overloads (background(ColorToken) etc.) are unavailable
// inside disabled blocks. Workaround: read LocalPaletteXxx.currentValue manually and call
// the raw Color/Dp overloads. This resolves when Foundation ships
// CustomStyle<T>.disabled(T.() -> Unit).
interface PaletteStyleScope : StyleScope {

    // ── Theme objects ────────────────────────────────────────────────────────────────────────
    val colorScheme: ColorScheme get() = LocalPaletteColorScheme.currentValue
    val typography: Typography get() = LocalPaletteTypography.currentValue
    val spacing: Spacing get() = LocalPaletteSpacing.currentValue
    val shapes: ShapeScheme get() = LocalPaletteShapes.currentValue
    val styles: Styles get() = LocalPaletteStyles.currentValue
    val formats: Formats get() = LocalPaletteFormats.currentValue
    val indication: Indication get() = LocalPaletteIndication.currentValue

    // ── Color ────────────────────────────────────────────────────────────────────────────────
    fun background(token: ColorToken) = background(token.toColor(colorScheme))
    fun contentColor(token: ColorToken) = contentColor(token.toColor(colorScheme))

    // ── Shape ────────────────────────────────────────────────────────────────────────────────
    fun shape(token: ShapeToken) = shape(token.toShape(shapes).toComposeShape())

    // ── Border ───────────────────────────────────────────────────────────────────────────────
    fun border(token: BorderStyleToken) {
        val borderStyle = token.toStyle(styles.border)
        val color = borderStyle.color.toColor(colorScheme)
        val shape = borderStyle.shape.toComposeShape(shapes)
        border(borderStyle.width, color, shape)
    }

    // ── Typography ───────────────────────────────────────────────────────────────────────────
    // Resolves to a Compose TextStyle (NOT Palette's TextStyle wrapper).
    // TextFormat features (capitalization, word delimiters) require Text(style = paletteTextStyle).
    fun textStyle(token: TypographyToken) = textStyle(token.toComposeTextStyle(typography))

    // ── Spacing ──────────────────────────────────────────────────────────────────────────────
    fun contentPadding(token: SpacingToken) = contentPadding(token.toSpacing(spacing))
    fun contentPaddingHorizontal(token: SpacingToken) = contentPaddingHorizontal(token.toSpacing(spacing))
    fun contentPaddingVertical(token: SpacingToken) = contentPaddingVertical(token.toSpacing(spacing))
    fun contentPaddingStart(token: SpacingToken) = contentPaddingStart(token.toSpacing(spacing))
    fun contentPaddingEnd(token: SpacingToken) = contentPaddingEnd(token.toSpacing(spacing))
    fun contentPaddingTop(token: SpacingToken) = contentPaddingTop(token.toSpacing(spacing))
    fun contentPaddingBottom(token: SpacingToken) = contentPaddingBottom(token.toSpacing(spacing))
}

typealias PaletteStyle = CustomStyle<PaletteStyleScope>

// Chains two PaletteStyle lambdas; the second wins on conflicting properties.
// Mirrors Modifier.then() — use to layer overrides on top of a default style:
//   Surface(style = SurfaceDefaults.style.then(PaletteStyle { background(ColorToken.Primary) }))
operator fun PaletteStyle.then(other: PaletteStyle): PaletteStyle =
    PaletteStyle { this@then.invoke(this); other.invoke(this) }
