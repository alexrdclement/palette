# Component demos

Requirements for the interactive demos that accompany every component. Referenced from
**[Components.md](Components.md)**; every component MUST have a demo that follows these rules.

## Location

- Demos MUST live in the `:components:demo` module, in a package that mirrors the component's package
  (a `components/media/SkipButton.kt` component has a `components/demo/media/SkipButtonDemo.kt` demo).
- Each demo SHOULD exist in its own `<ComponentName>Demo.kt` file.

## File contents & order

A demo file is ordered:

1. **`<Component>Demo(modifier, state, control)`** — the entry composable. It MUST wrap the component
   in a `Demo` (or `DemoList`), pass `control.controls` to it, and render the component inside the
   `Demo` content. `state`/`control` default to their `remember…` factories.
2. **`DemoScope.<Component>Demo(...)`** *(optional)* — an extension on `DemoScope` that renders the
   actual component, so it can be composed inside an existing `Demo`. When present, the entry
   composable delegates to it.
3. **State** — `remember<Component>DemoState(...)`, a `@Stable class <Component>DemoState(...)`, and
   (when the state is savable) a `<Component>DemoStateSaver`.
4. **Control** — `remember<Component>DemoControl(state)` and a `@Stable class
   <Component>DemoControl(state)`.
5. **`@Preview`** — a private preview of the demo.

Simple demos MAY omit the State/Control classes and instead hold state inline with
`remember { mutableStateOf(...) }` and build the `controls` list inline.

## State & Control classes

- State and Control classes MUST be `@Stable`.
- Members MUST be ordered properties first, then functions.
- Trivial docstrings (restating the obvious) MUST NOT be added.
- Editable state exposed to controls MUST be `mutableStateOf`-backed; setters that only the demo
  framework mutates SHOULD be `internal set` (use a public setter only when an app screen must set
  it, e.g. `ButtonStyleScreen`).

## Savers

- A savable Compose/framework type MUST be persisted with a shared `*Saver` from
  `components/util` (`ColorSaver`, `DpSizeSaver`, `PaddingValuesSaver`, …) via
  `save(value, TheSaver, this)` / `restore(map[key], TheSaver)`, rather than hand-serialising it
  field-by-field.
- If a needed saver does not exist, add it next to the others in `components/util` and reuse it.

## Controls

- A control that edits a `*Style` property SHOULD be grouped under a `Control.ControlColumn` named
  `"Style"` (expanded by default), so the demo reads as editing the component's style. Non-style
  inputs (enabled, playing, width, …) stay at the top level.
- Reusable control helpers (e.g. `paddingValuesControls`, `enumControl`) SHOULD be used instead of
  re-building the same control shape inline.
