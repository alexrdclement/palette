package com.alexrdclement.palette.theme.style

import androidx.compose.foundation.style.Style

// PaletteStyle is today a typealias for Foundation's Style so all existing Style { } lambdas and
// XxxDefaults.style properties compile without change.
//
// When JetBrains Compose Multiplatform ships Foundation 1.12.0-alpha03+ (which adds
// CustomStyle<T>), this becomes:
//
//   typealias PaletteStyle = CustomStyle<PaletteStyleScope>
//
// And PaletteStyleScope is promoted from the StyleScope extension functions in
// StyleScopeExtensions.kt to a proper interface:
//
//   @ExperimentalFoundationStyleApi
//   interface PaletteStyleScope : StyleScope {
//       val colorScheme: ColorScheme get() = LocalPaletteColorScheme.currentValue
//       val typography: Typography get() = LocalPaletteTypography.currentValue
//       val spacing: Spacing get() = LocalPaletteSpacing.currentValue
//       val shapes: ShapeScheme get() = LocalPaletteShapes.currentValue
//       val styles: Styles get() = LocalPaletteStyles.currentValue
//       val formats: Formats get() = LocalPaletteFormats.currentValue
//       val indication: Indication get() = LocalPaletteIndication.currentValue
//
//       fun background(token: ColorToken) = background(token.toColor(colorScheme))
//       fun contentColor(token: ColorToken) = contentColor(token.toColor(colorScheme))
//       fun shape(token: ShapeToken) = shape(token.toShape(shapes).toComposeShape())
//       fun border(token: BorderStyleToken) { ... }
//       fun textStyle(token: TypographyToken) = textStyle(token.toComposeTextStyle(typography))
//       fun contentPadding(token: SpacingToken) = contentPadding(token.toSpacing(spacing))
//       fun contentPaddingHorizontal(token: SpacingToken) = contentPaddingHorizontal(token.toSpacing(spacing))
//       fun contentPaddingVertical(token: SpacingToken) = contentPaddingVertical(token.toSpacing(spacing))
//       fun contentPaddingStart(token: SpacingToken) = contentPaddingStart(token.toSpacing(spacing))
//       fun contentPaddingEnd(token: SpacingToken) = contentPaddingEnd(token.toSpacing(spacing))
//       fun contentPaddingTop(token: SpacingToken) = contentPaddingTop(token.toSpacing(spacing))
//       fun contentPaddingBottom(token: SpacingToken) = contentPaddingBottom(token.toSpacing(spacing))
//   }
//
// At that point StyleScopeExtensions.kt is deleted and all `style: Style` / `Style { }` usages
// in component files are renamed to `style: PaletteStyle` / `PaletteStyle { }`.
typealias PaletteStyle = Style
