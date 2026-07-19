# Design tokens

Requirements for adding and organizing theme tokens, plus a reference for the token model that
`PaletteTheme` exposes. The `MUST`/`SHOULD`/`MAY` items are rules a new token is expected to follow;
the surrounding prose explains the model those rules build on. Component-tier styling has its own
requirements in **[Components.md](Components.md)** — this document covers the primitive/semantic model
those build on and cross-links rather than repeats the component rules.

## Tiers

Tokens are organized into three tiers, each building on the one before:

- **Primitive** — unopinionated, context-free raw values (a font family, a shape, an indication
  effect). Primitives carry no design intent; they are the catalog a theme chooses from.
- **Semantic** — design-intent tokens that give primitives meaning in context (`ColorToken.Primary`,
  `TypographyToken.Display`, `ShapeToken.Surface`). A semantic token selects a primitive (or, for
  color, holds the values directly) and is what app code and component styles reference.
- **Component** — per-component styling assembled from semantic tokens (`ButtonStyleTokenSet`,
  `TextStyleTokenSet`), resolving to the headless `*Style` a component consumes.

- A new token MUST be placed in the tier that matches its role, by this test: a **capability** is
  primitive, a **choice** is semantic, a **component's assembled styling** is component-tier.
  "ColorSplit is an available effect" is primitive; "interactions use ColorSplit" is semantic; "the
  primary button uses these tokens" is component-tier.
- Tokens MUST depend only downward — component references semantic, semantic references primitive. A
  primitive MUST NOT reference a semantic or component token.

## Accessors

Each tier is reachable from `PaletteTheme`, and each has one editable holder type provided through one
composition local:

| Tier | Accessor | Holder | Local |
| --- | --- | --- | --- |
| Primitive | `PaletteTheme.primitive` | `PrimitiveTokens` | `LocalPrimitiveTokens` |
| Semantic | `PaletteTheme.semantic` | `SemanticTokens` | `LocalSemanticTokens` (+ `LocalIsDarkMode`) |
| Component | `PaletteTheme.component` | `ComponentTokens` | `LocalComponentTokens` |

- Each tier MUST expose exactly one holder type, provided through exactly one composition local, and be
  reachable via `PaletteTheme.<tier>`. A new token belongs on its tier's existing holder — it MUST NOT
  introduce a second holder or a per-value composition local.

`PaletteTheme` provides these locals from the tokens passed to it:

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

Primitives are stored on `PrimitiveTokens` as maps from a primitive token enum to its resolved value,
exposed through `PaletteTheme.primitive`:

- `PaletteTheme.primitive.fontFamily` — `Map<FontFamily, ComposeFontFamily>`
- `PaletteTheme.primitive.fontWeight` — `Map<FontWeight, ComposeFontWeight>`
- `PaletteTheme.primitive.fontStyle` — `Map<FontStyle, ComposeFontStyle>`
- `PaletteTheme.primitive.shape` — `Map<ShapePrimitiveToken, Shape>`
- `PaletteTheme.primitive.indication` — `Map<IndicationPrimitiveToken, IndicationTokenSet>`

The map form keeps primitives editable (e.g. the `RoundRect` shape's corner radius, or an indication's
amount and colour mode) and in one place. Requirements for a new primitive family:

- A primitive value MUST be unopinionated — a raw capability with no design intent. Anything that
  encodes a *choice* belongs in the semantic tier.
- A primitive family MUST be exposed as a `Map<*PrimitiveToken, Value>` on `PrimitiveTokens`,
  populated from the token enum (`entries.associateWith { … }`).
- A primitive token enum MUST either convert itself to its value (`FontFamily.Monospace
  .toComposeFontFamily()`) or carry a `default` (`ShapePrimitiveToken.RoundRect.default`,
  `IndicationPrimitiveToken.ColorSplit.default`).
- A primitive whose value bundles parameters (like an indication's amount + colour mode) SHOULD use a
  `*TokenSet` that builds the value via a resolver (see [Token sets](#token-sets)); simple primitives
  (shape, font) resolve directly.

## Semantic tier

Semantic tokens are the editable inputs on `SemanticTokens`; `PaletteTheme.semantic.<family>` (color,
typography, shape, dimension, interaction, format, …) exposes the value components consume, resolving
where needed. Most families follow one shape: a `*Token` enum selects a primitive, and the accessor
resolves that selection through the tier below. A few families carry extra behavior worth calling out:

- **Color** holds its `light`/`dark` `ColorScheme`s directly (there is no color primitive tier), and
  `PaletteTheme.semantic.color` resolves the active one on read from `LocalIsDarkMode` — dark mode is
  runtime state, not a token.
- **Typography** resolves a whole ramp: each `TypographyToken` selects primitive
  `FontFamily`/`FontWeight`/`FontStyle` tokens plus its own size/line-height/letter-spacing, so its
  accessor memoizes the resolved `Typography`.
- **Interaction** additionally exposes `PaletteTheme.semantic.indication`, a convenience that resolves
  the default interaction token to its `Indication` for the many component styles that just want "the"
  indication.
- **Dimension** groups the size families under `PaletteTheme.semantic.dimension`, split by the *role*
  a value plays:
  - `.spacing` — space **between** items (gaps in a `Row`/`Column`, item/row/content spacing). The
    `Spacing` scale is a set of `Dp` steps (`none`/`xs`/`small`/`medium`/`large`) selected by
    `SpacingToken`; `none` (= `0.dp`) is a real step so a zero gap or a zeroed padding edge is a token,
    not a literal.
  - `.padding` — space **inside** a component (internal insets). A `PaddingScheme` of named
    `PaddingValuesToken`s that resolve to a `PaddingValuesTokenSet` (a `SpacingToken` per edge) and then
    to `PaddingValues` through the spacing scale — padding never embeds raw `Dp`s, it selects spacing
    tokens per edge.
  - A `PaddingValuesToken` exists **only for an inset whose edges differ** — asymmetric (`Default` =
    `Large`/`Small`/`Large`/`Small`) or directional (some edges `None`). A **uniform** inset carries no
    per-edge information, so it is expressed straight from a single spacing step
    (`PaddingValues(dimension.spacing.medium)`), not a token. (Element/glyph *sizes* — how big a thing
    is — are a separate concern from spacing/padding and are not part of this scale.)

Requirements for a new semantic token:

- A semantic token MUST express design intent by **selecting** a primitive (`ShapeToken` → a
  `ShapePrimitiveToken`, `TypographyToken` → primitive font tokens) rather than embedding a raw value —
  except color, which holds its `light`/`dark` `ColorScheme`s directly because there is no color
  primitive tier.
- A semantic accessor MUST resolve on read from its tier local and MUST NOT cache a resolved value on
  the holder. Resolution that is more than a cheap branch SHOULD be memoized in the accessor (as
  typography's ramp is).
- Runtime state that isn't a token (dark mode) MUST NOT live on `SemanticTokens`; it is provided
  separately (`LocalIsDarkMode`) and folded in during resolution.

## Component tier

Component tokens assemble a component's style out of semantic tokens. `ComponentTokens` holds one
`Map<*Token, *TokenSet>` per token family (`text`, `button`, `surface`, `border`, …), and
`PaletteTheme.component` exposes the resolved styles through the `*Styles` facades that mirror the
component packages:

- `PaletteTheme.component.core.button.primary`, `…core.surface.default`, `…core.text.titleMedium`
- `PaletteTheme.component.media.playPauseButton`, `…color.colorDisplay`, …

The requirements for a component `*Style` getter and its `*TokenSet` (reuse existing theme values,
expose a resolver, `.copy()` only in the theme layer, …) live in
**[Components.md → Theme](Components.md#theme)** and
**[Components.md → Token sets](Components.md#token-sets)** — they are not repeated here.

## Token sets

All three tiers share one editing pattern: a token whose value is itself a bundle of other tokens or
parameters is backed by a `*TokenSet`. A `*TokenSet` holds the token selections and literals for one
token (e.g. `TypographyTokenSet` holds primitive `FontFamily`/`FontWeight`/`FontStyle` plus
size/line-height/letter-spacing; `IndicationTokenSet` holds an effect's parameters such as amount and
colour mode). Most are data classes; `IndicationTokenSet` is a sealed type with one variant per effect.

Requirements for a new `*TokenSet`:

- Each `*Token` enum entry MUST carry a `default: *TokenSet`.
- The current set for every token MUST be stored in the tier holder as a `Map<*Token, *TokenSet>` (one
  map per family), populated by `*Token.entries.associateWith { it.default }` — e.g.
  `SemanticTypography.tokens`, `ComponentTokens.button`.
- A `*TokenSet` MUST expose a resolver (`toComposeTextStyle`, `toComponentStyle`, `toIndication`, …)
  that turns it into the final value, looking any token selections up through the tier below.

This is why `PrimitiveTokens.indication`, `SemanticTypography.tokens`, `ShapeScheme`,
`InteractionScheme`, and `ComponentTokens` all read the same way: a map of tokens to selections or
parameters that resolves on read.

## Editing tokens

The demo app's theme editor drives tokens through `ThemeController`, which wraps a `ThemeState`
(`primitive`, `semantic`, `component`, `isDarkMode`, and the resolved `colorScheme`).

- Edits MUST go through the tier's `ThemeController.update*` (or `setIsDarkMode`) and replace tokens by
  value; an editor MUST NOT mutate a resolved value:
  - `themeController.updatePrimitive { it.copy(shape = it.shape + (ShapePrimitiveToken.RoundRect to …)) }`
  - `themeController.updateSemantic { it.copy(interaction = it.interaction.copy(IndicationToken.Default, …)) }`
  - `themeController.updateComponent { it.copy(button = it.button + (token to …)) }`
  - `themeController.setIsDarkMode(true)`

`PaletteTheme` re-provides the tier locals from the updated `ThemeState`, and the `@Composable`
accessors re-resolve. The editor-screen requirements are in
**[Components.md → Demo app: theme editor](Components.md#demo-app-theme-editor)**.
