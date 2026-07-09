# Components

Requirements for building components in Palette. This is a requirements document, not a tutorial:
each item is a rule that new and existing components are expected to follow.

## Summary

- Components MUST live in the `:components` module and be grouped into packages by use case
  (`core`, `layout`, `media`, `menu`, `money`, `navigation`, `auth`, `color`, `geometry`, …).
- Each component SHOULD exist in its own file. A file with tightly-coupled helpers (e.g. an icon
  drawn only for one button) MAY keep them together, but unrelated components MUST NOT share a file.
- A component file MUST be ordered:
  1. the `*Style` data class (named `<ComponentName>Style`),
  2. the component `@Composable`,
  3. any helper functions/private composables,
  4. a `@Preview` (private).
- Components MUST be headless with respect to the theme: the `:components` module MUST NOT depend on
  `:theme`. All visual values come in through the `*Style` parameter (see below); the theme supplies
  those values from the outside.

## Style data classes

- Every visually-styled component MUST expose a `<ComponentName>Style` data class, and the component
  MUST take it as a `style: <ComponentName>Style = <ComponentName>Style()` parameter.
- Style data classes contain the values for visually styling the component (colors, shapes, borders,
  text styles, indication, spacing/padding, sizes).
- Style data classes MUST provide reasonable defaults:
  - `Dp` values MUST be in increments of `4.dp`.
  - `Color` values MUST default to `Color.Unspecified`.
  - Defaults represent the *unstyled* component (transparent/neutral); the themed look is supplied
    by the theme layer, not by the defaults.
- Styles MUST contain child component styles where applicable rather than re-declaring the child's
  fields. For example, an `AuthButtonStyle` contains a `ButtonStyle`; a `SkipButtonStyle` contains a
  `ButtonStyle` and a `SkipIconStyle`.
- Components MUST NOT contain hardcoded visual styling values. Every color, shape, size, padding, or
  spacing that affects appearance MUST be read from the `style`. (Layout values driven by runtime
  constraints — window insets, available space — are not "visual styling" and MAY remain parameters;
  everything a theme would want to control belongs in the style.)
- A component MUST render the `style` it is given. It MUST NOT construct or mutate a child style with
  `.copy()` to inject a value it decided itself — that value belongs in the style, supplied by the
  theme. (Building a framework value such as a `PaddingValues` from `style` fields is fine; overriding
  a themed sub-style is not.)

## Theme & Styles

Component styles are resolved and exposed to app code through the theme.

- Resolved component styles MUST be reachable via `PaletteTheme.styles`, which returns the
  `PaletteStyles` facade. `PaletteStyles` exposes a chain of `val` accessors that mirror the
  component package structure, e.g. `PaletteTheme.styles.core.button`,
  `PaletteTheme.styles.media.playPauseButton`, `PaletteTheme.styles.color.colorPicker`.
- Each package's accessors live in a `*Styles` object in `:theme` (`CoreStyles`, `LayoutStyles`,
  `MediaStyles`, …) as `@Composable get()` properties that return a fully-resolved `*Style`.
- A `*Style` getter MUST reuse existing theme values wherever one applies rather than inventing a
  literal:
  - content on a `Surface` uses `PaletteTheme.colorScheme.onSurface`;
  - `Dp` values use `PaletteTheme.spacing.*` tokens;
  - shapes use `PaletteTheme.shapeScheme.*` / shape tokens; indication uses `PaletteTheme.indication`.
- `*Style` getters MAY compose sub-styles from other getters (e.g. `SkipButtonStyle.buttonStyle =
  CoreStyles.button.secondary`). `.copy()` to assemble a themed style **in the theme layer** is
  allowed — that is the theme being the source of truth. `.copy()` inside a component is not.

### Token sets

Some styles are edited token-by-token (so the theme editor can tweak them). These use an extra
aliasing layer:

- A `*TokenSet` data class primarily contains other token values (e.g. `ButtonStyleTokenSet` holds a
  `containerColor: ColorToken`, `shape: ShapeToken`, `borderStyle: BorderStyleToken?`,
  `contentPadding: PaddingValuesTokenSet`).
- Each `*Token` enum entry carries a `default: *TokenSet`.
- The current token set for every token is stored in the `Styles` class as a
  `Map<*Token, *TokenSet>` (one map per token family), provided via `LocalStyles` and edited through
  `ThemeController`.
- A `*TokenSet` MUST expose a resolver helper (e.g. `toComponentStyle()` / `toTextStyle()`) that
  turns it into the final component `*Style`. The package `*Styles` getters call these resolvers.

## `:theme:components` wrappers

The `:theme:components` module contains theme-aware wrappers over the headless components.

- Each wrapper MUST mirror its base component's package and file structure (a base
  `components/core/Surface.kt` has a wrapper `theme/components/core/Surface.kt`), and MUST live in
  package `com.alexrdclement.palette.theme.components.*`.
- A wrapper MUST expose the **same API** as the base component (same name, same parameters), except
  that its `style` parameter defaults to the matching themed value, e.g.
  `style: SurfaceStyle = PaletteTheme.styles.core.surface.default`.
- A wrapper MUST do nothing but default the style and delegate to the base component. It MUST NOT add
  behavior. Callers that want the unstyled/headless component use the base `:components` version;
  callers that want the themed default use the wrapper.

## Component demos

Every component MUST have a demo in the `:components:demo` module. Demos are **required** and have
their own structural requirements — see **[Demos.md](Demos.md)**.

## Demo app: component demo entry

Each component group is wired into the demo app under
`app/.../demo/components/<group>/`.

### Screen

- A `<Group>ComponentScreen(component, onNavigateUp, onThemeClick)` composable MUST render a
  `Scaffold` with a `DemoTopBar` (title from the component, `onNavigateUp`, `onThemeClick`) and, in
  the content slot, a `when (component)` that dispatches to each `<Component>Demo(modifier =
  Modifier.padding(innerPadding))`.

### Navigation

Under `app/.../demo/components/<group>/navigation/`:

- A `<Group>Component : CatalogItem` enum MUST list the components in the group (its `title` drives
  the catalog entry).
- `<Group>ComponentsRoutes.kt` MUST define:
  - a sealed `<Group>ComponentsRoute : NavKey`;
  - a `<Group>ComponentsGraph : NavGraphRoute` (with a `pathSegment`);
  - a `<Group>ComponentCatalogRoute`;
  - a per-component `<Group>ComponentRoute : EnumNavKey<<Group>Component>`.
- `<Group>ComponentsNav.kt` MUST define:
  - `NavGraphBuilder.<group>ComponentsNavGraph()` — a `navGraph(root, start)` declaring the catalog
    route and the (wildcard) component route;
  - `EntryProviderScope<NavKey>.<group>ComponentsEntryProvider(navController)` — a
    `catalogEntry<…Catalog, <Group>Component>` (navigating to each component route) plus an
    `entry<<Group>ComponentRoute>` that renders `<Group>ComponentScreen`.
- The group MUST be registered in `demo/components/navigation/ComponentsNav.kt`: add its `…NavGraph()`
  to `componentsNavGraph()`, its `…EntryProvider(navController)` to `componentsEntryProvider`, and a
  corresponding entry to the top-level `Component` enum.

## Demo app: theme / style editor

Editors for token-set-backed styles live under `app/.../theme/styles/`.

### Screen

- A `<X>StyleScreen(themeController, onNavigateUp)` composable MUST render a `Scaffold` +
  `DemoTopBar`, then a `DemoList` over the token family's `*Token.entries`, with per-token controls.
- Controls MUST read the current token set from `themeController` (via `ThemeState.styles`) and write
  edits back with `themeController.setStyles(styles.copy(<family> = styles.<family> + (token to …)))`.
  The screen MUST NOT mutate resolved styles directly.

### Navigation

Under `app/.../theme/styles/navigation/`:

- A `Styles : CatalogItem` enum MUST list the editable style families.
- `StylesRoutes.kt` MUST define a sealed `StylesRoute : NavKey`, a `StylesGraph : NavGraphRoute`, a
  `StylesCatalogRoute`, and one route per family (`ButtonStylesRoute`, `TextStylesRoute`, …).
- `StylesNav.kt` MUST define `NavGraphBuilder.stylesNavGraph()` (declaring the catalog + per-family
  routes) and `EntryProviderScope<NavKey>.stylesEntryProvider(navController, themeController)` (a
  `catalogEntry<StylesCatalogRoute, Styles>` plus an `entry<…>` per family rendering the matching
  `<X>StyleScreen(themeController, onNavigateUp)`).
