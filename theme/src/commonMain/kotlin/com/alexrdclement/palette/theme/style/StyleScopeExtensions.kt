package com.alexrdclement.palette.theme.style

import androidx.compose.foundation.Indication
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

// StyleScope extensions that bridge Palette's token system and theme objects into the
// Foundation Style API.
//
// Inside Style lambdas, CompositionLocals must be read via .currentValue (not .current) because
// the lambda executes outside of Composition during the draw/layout phase.
//
// These extensions are global (available in any Style { } block). When Foundation ships
// CustomStyle<T> in Compose Multiplatform, they will be promoted into PaletteStyleScope
// as interface methods, and StyleScopeExtensions.kt will be deleted.

// ── Full theme object access ──────────────────────────────────────────────────────────────────
// Every Palette theme object is directly readable inside Style / PaletteStyle lambdas.
// Use these when a token shorthand doesn't exist or when you need to derive an ad-hoc value.

val StyleScope.colorScheme: ColorScheme get() = LocalPaletteColorScheme.currentValue
val StyleScope.typography: Typography get() = LocalPaletteTypography.currentValue
val StyleScope.spacing: Spacing get() = LocalPaletteSpacing.currentValue
val StyleScope.shapes: ShapeScheme get() = LocalPaletteShapes.currentValue
val StyleScope.styles: Styles get() = LocalPaletteStyles.currentValue
val StyleScope.formats: Formats get() = LocalPaletteFormats.currentValue

// indication is a behavioral property with no StyleScope method equivalent; exposed as a read-only
// property so components can reference the ambient indication inside custom Style lambdas.
val StyleScope.indication: Indication get() = LocalPaletteIndication.currentValue

// ── Color ─────────────────────────────────────────────────────────────────────────────────────

fun StyleScope.background(token: ColorToken) =
    background(token.toColor(colorScheme))

// contentColor() propagates to child BasicText composables via Foundation's TextStyleProviderNode
// (modifier-node traversal). ComposeFoundationFlags.isInheritedTextStyleEnabled defaults to true,
// so this works without any app-level flag setup.
fun StyleScope.contentColor(token: ColorToken) =
    contentColor(token.toColor(colorScheme))

// ── Shape ─────────────────────────────────────────────────────────────────────────────────────

fun StyleScope.shape(token: ShapeToken) =
    shape(token.toShape(shapes).toComposeShape())

// ── Border ────────────────────────────────────────────────────────────────────────────────────

fun StyleScope.border(token: BorderStyleToken) {
    val borderStyle = token.toStyle(styles.border)
    val color = borderStyle.color.toColor(colorScheme)
    val shape = borderStyle.shape.toComposeShape(shapes)
    border(borderStyle.width, color, shape)
}

// ── Typography ────────────────────────────────────────────────────────────────────────────────
// Accepts a TypographyToken and resolves it to a Compose TextStyle (NOT Palette's TextStyle
// wrapper). TextFormat features (capitalization, word delimiters) are not available here;
// use Text(style = PaletteTheme.styles.text.xxx) for those.

fun StyleScope.textStyle(token: TypographyToken) =
    textStyle(token.toComposeTextStyle(typography))

// ── Spacing ───────────────────────────────────────────────────────────────────────────────────
// Token-aware content padding. Note: thickness: Dp on Dividers is a layout size, not content
// padding; it stays as a direct parameter.

fun StyleScope.contentPadding(token: SpacingToken) =
    contentPadding(token.toSpacing(spacing))

fun StyleScope.contentPaddingHorizontal(token: SpacingToken) =
    contentPaddingHorizontal(token.toSpacing(spacing))

fun StyleScope.contentPaddingVertical(token: SpacingToken) =
    contentPaddingVertical(token.toSpacing(spacing))

fun StyleScope.contentPaddingStart(token: SpacingToken) =
    contentPaddingStart(token.toSpacing(spacing))

fun StyleScope.contentPaddingEnd(token: SpacingToken) =
    contentPaddingEnd(token.toSpacing(spacing))

fun StyleScope.contentPaddingTop(token: SpacingToken) =
    contentPaddingTop(token.toSpacing(spacing))

fun StyleScope.contentPaddingBottom(token: SpacingToken) =
    contentPaddingBottom(token.toSpacing(spacing))
