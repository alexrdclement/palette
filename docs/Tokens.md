# Design tokens

How Palette's theme is structured. This is an architecture and reference document for the token model
that `PaletteTheme` exposes. For the rules a new **component** must follow, see
**[Components.md](Components.md)** — this document explains the token layers those rules build on.

## Tiers

Tokens are organized into three tiers, each building on the one before:

- **Primitive** — unopinionated, context-free raw values (a font family, a shape, an indication
  effect). Primitives carry no design intent; they are the catalog a theme chooses from.
- **Semantic** — design-intent tokens that give primitives meaning in context (`ColorToken.Primary`,
  `TypographyToken.Display`, `ShapeToken.Surface`). A semantic token selects a primitive (or, for
  color, holds the values directly) and is what app code and component styles reference.
- **Component** — per-component styling assembled from semantic tokens (`ButtonStyleTokenSet`,
  `TextStyleTokenSet`), resolving to the headless `*Style` a component consumes.

The dividing rule: a **capability** is a primitive; a **choice** is semantic; a **component's
assembled styling** is component-tier. "ColorSplit is an available effect" is primitive; "interactions
use ColorSplit" is semantic; "the primary button uses these tokens" is component-tier.

## Accessors

Each tier is reachable from `PaletteTheme`, and each has one editable holder type provided through one
composition local:

| Tier | Accessor | Holder | Local |
| --- | --- | --- | --- |
| Primitive | `PaletteTheme.primitive` | `PrimitiveTokens` | `LocalPrimitiveTokens` |
| Semantic | `PaletteTheme.semantic` | `SemanticTokens` | `LocalSemanticTokens` (+ `LocalIsDarkMode`) |
| Component | `PaletteTheme.component` | `ComponentTokens` | `LocalComponentTokens` |

`PaletteTheme` provides all four locals from the tokens passed to it:

```kotlin
PaletteTheme(
    primitive: PrimitiveTokens = PrimitiveTokens(),
    semantic: SemanticTokens = SemanticTokens(),
    component: ComponentTokens = ComponentTokens(),
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
)
```

## Primitive tier

Primitives are stored on `PrimitiveTokens` as maps from a primitive token enum to its resolved Compose
value, and exposed through `PaletteTheme.primitive`:

- `PaletteTheme.primitive.fontFamily` — `Map<FontFamily, ComposeFontFamily>`
- `PaletteTheme.primitive.fontWeight` — `Map<FontWeight, ComposeFontWeight>`
- `PaletteTheme.primitive.fontStyle` — `Map<FontStyle, ComposeFontStyle>`
- `PaletteTheme.primitive.shape` — `Map<ShapePrimitiveToken, Shape>`
- `PaletteTheme.primitive.indication` — `Map<IndicationPrimitiveToken, IndicationTokenSet>`

The map form makes primitives editable (e.g. the `RoundRect` shape's corner radius, or an
indication's amount and colour mode) and keeps the value in one place. A primitive token enum either
converts itself (`FontFamily.Monospace.toComposeFontFamily()`) or carries its default value
(`ShapePrimitiveToken.RoundRect.default`, `IndicationPrimitiveToken.ColorSplit.default`). Shape and
font primitives resolve directly; an `IndicationTokenSet` builds its `Indication` via `toIndication()`
from the stored parameters (see [Token sets](#token-sets)).

## Semantic tier

Semantic tokens are the editable inputs on `SemanticTokens`; `PaletteTheme.semantic` exposes the
values components consume, resolving where needed:

- `PaletteTheme.semantic.color` — `ColorScheme`. `SemanticTokens.colors` holds both a `light` and a
  `dark` scheme; the active one is resolved on read from `LocalIsDarkMode` (dark mode is runtime
  state, not a token).
- `PaletteTheme.semantic.typography` — `Typography`. Each `TypographyToken` carries a default
  `TypographyTokenSet` that **selects** primitive `FontFamily`/`FontWeight`/`FontStyle` tokens plus its
  own size/line-height/letter-spacing; `resolve` looks those selections up through the primitive maps.
  The resolved ramp is memoized.
- `PaletteTheme.semantic.shape` — `ShapeScheme`, mapping each `ShapeToken` (Primary, Secondary,
  Tertiary, Surface) to a `ShapePrimitiveToken`. `ShapeToken.toShape()` resolves through the primitive
  shape map.
- `PaletteTheme.semantic.spacing` — `Spacing`; `SpacingToken` (XS, Small, Medium, Large) reads a `Dp`.
- `PaletteTheme.semantic.interaction` — `InteractionScheme`, mapping each `IndicationToken` (Default)
  to an `IndicationPrimitiveToken`. `PaletteTheme.semantic.indication` is a convenience that resolves
  the default interaction token to its `Indication`, for the many component styles that just want "the"
  indication.
- `PaletteTheme.semantic.format` — `Formats` (text, number, money, date-time format schemes).

Semantic values resolve **on read**, the same way component styles do. Color is a cheap branch;
typography is memoized in its accessor.

## Component tier

Component tokens assemble a component's style out of semantic tokens. `ComponentTokens` holds one
`Map<*Token, *TokenSet>` per token family (`text`, `button`, `surface`, `border`, …), and
`PaletteTheme.component` exposes the resolved styles through the `*Styles` facades that mirror the
component packages:

- `PaletteTheme.component.core.button.primary`, `…core.surface.default`, `…core.text.titleMedium`
- `PaletteTheme.component.media.playPauseButton`, `…color.colorDisplay`, …

Each package's accessors live in a `*Styles` object in `:theme` (`CoreStyles`, `MediaStyles`, …) as
`@Composable get()` properties returning a fully-resolved `*Style`. See
**[Components.md → Theme](Components.md#theme)** for the requirements a `*Style` getter must follow.

## Token sets

All three tiers share one editing pattern: a token whose value is itself a bundle of other tokens or
parameters is backed by a `*TokenSet`.

- A `*TokenSet` holds the token selections and literals for one token (e.g. `TypographyTokenSet` holds
  a primitive `FontFamily`/`FontWeight`/`FontStyle` plus size/line-height/letter-spacing;
  `ButtonStyleTokenSet` holds a `ColorToken`, a `ShapeToken`, an optional `BorderStyleToken`, and a
  `PaddingValuesTokenSet`; `IndicationTokenSet` holds an effect's parameters such as amount and colour
  mode). Most are data classes; `IndicationTokenSet` is a sealed type with one variant per effect.
- Each `*Token` enum entry carries a `default: *TokenSet`.
- The current set for every token is stored in the tier holder as a `Map<*Token, *TokenSet>` (one map
  per family) — e.g. `SemanticTypography.tokens`, `ComponentTokens.button` — populated by
  `*Token.entries.associateWith { it.default }`.
- A `*TokenSet` exposes a resolver that turns it into the final value (`toComposeTextStyle`,
  `toComponentStyle`, `toTextStyle`, `toIndication`), looking any token selections up through the tier
  below.

This is why `PrimitiveTokens.indication`, `SemanticTypography.tokens`, `ShapeScheme`,
`InteractionScheme`, and `ComponentTokens` all read the same way: a map of tokens to selections or
parameters that resolves on read.

## Editing tokens

The demo app's theme editor drives tokens through `ThemeController`, which wraps a `ThemeState`
(`primitive`, `semantic`, `component`, `isDarkMode`, and the resolved `colorScheme`). Edits are made
per tier and never mutate resolved values:

- `themeController.updatePrimitive { it.copy(shape = it.shape + (ShapePrimitiveToken.RoundRect to …)) }`
- `themeController.updateSemantic { it.copy(interaction = it.interaction.copy(IndicationToken.Default, …)) }`
- `themeController.updateComponent { it.copy(button = it.button + (token to …)) }`
- `themeController.setIsDarkMode(true)`

`PaletteTheme` re-provides the tier locals from the updated `ThemeState`, and the `@Composable`
accessors re-resolve. See **[Components.md → Demo app: theme editor](Components.md#demo-app-theme-editor)**
for the editor-screen requirements.
