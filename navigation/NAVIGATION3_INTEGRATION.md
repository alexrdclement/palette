# Navigation3 Integration Guide

This document explains how the Palette navigation library integrates with Compose Multiplatform's navigation3 libraries.

## Overview

The Palette navigation library is now compatible with navigation3's `NavDisplay` and `EntryProvider` APIs while maintaining its own custom navigation features like:
- Deeplink support via `PathSegment`
- Dialog route handling with `isDialog` property
- NavGraph hierarchy and resolution
- Type-safe generic navigation with `NavState<T : NavKey>` and `NavController<T : NavKey>`

## Architecture

### Custom NavKey Interface

Your custom `NavKey` interface (defined in `navigation/src/commonMain/kotlin/.../navigation/NavKey.kt`) provides:

```kotlin
interface NavKey {
    val pathSegment: PathSegment  // For deeplink generation
    val isDialog: Boolean          // For dialog handling
}
```

**Key Point**: You do NOT need to extend navigation3's `NavKey` interface. The navigation3 libraries work with any serializable type, and our custom `NavKey` interface adds Palette-specific functionality on top.

### Type Safety

All navigation types are now generic:
- `NavState<T : NavKey>` - Holds the backstack of type `T`
- `NavController<T : NavKey>` - Provides navigation operations for type `T`

This allows type-safe navigation while maintaining flexibility.

## Using with navigation3's NavDisplay

There are two ways to render navigation in your app:

### Option 1: Manual Route Matching (Current Implementation)

```kotlin
@Composable
fun PaletteNav(
    themeController: ThemeController,
    navState: NavState<NavKey> = rememberPaletteNavState(),
    navController: NavController<NavKey> = rememberNavController(state = navState),
) {
    val currentRoute = navState.currentRoute ?: return

    // Manually check routes and render appropriate composables
    MainNav(route = currentRoute, navController = navController)
    ComponentsNav(route = currentRoute, navController = navController)
    // ...
}
```

### Option 2: NavDisplay with EntryProvider (New)

```kotlin
@Composable
fun PaletteNavWithDisplay(
    themeController: ThemeController,
    navState: NavState<NavKey> = rememberPaletteNavState(),
    navController: NavController<NavKey> = rememberNavController(state = navState),
) {
    NavDisplay(
        backStack = navState.backStack,
        entryProvider = entryProvider {
            paletteEntryProvider(
                navController = navController,
                themeController = themeController,
            )
        },
        onBack = { navController.goBack() },
    )
}
```

### Creating Entry Providers

Entry providers map route types to composables using the `entry<T>` syntax:

```kotlin
fun EntryProviderScope<NavKey>.mainEntryProvider(
    navController: NavController<NavKey>,
) {
    entry<MainGraph> {
        MainNav(
            route = MainGraph,
            navController = navController,
        )
    }
    entry<MainCatalogRoute> {
        MainNav(
            route = MainCatalogRoute,
            navController = navController,
        )
    }
}
```

See `app/composeApp/src/commonMain/kotlin/.../navigation/PaletteEntryProvider.kt` for a complete example.

## Serialization

All `NavKey` implementations must be `@Serializable` for state restoration:

```kotlin
@Serializable
@SerialName("main")
data object MainGraph : NavKey {
    override val pathSegment = "main".toPathSegment()
}
```

### Polymorphic Serialization

Each navigation domain provides its own `SerializersModule` that registers all route types:

```kotlin
val mainSerializersModule = SerializersModule {
    polymorphic(NavKey::class) {
        subclass(MainGraph::class)
        subclass(MainCatalogRoute::class)
    }
}
```

These are combined in `NavKeySerialization.kt` to create the app-wide `appNavKeySerializersModule` and `NavKeyJson` instance.

## Benefits of navigation3 Integration

Using `NavDisplay` and `EntryProvider` provides:

1. **Better Animations**: Access to navigation3's built-in transition animations
2. **Shared Elements**: Support for shared element transitions between screens
3. **Predictive Back**: Integration with Android's predictive back gestures
4. **Scene Strategies**: Flexible rendering strategies (stack, single, etc.)
5. **Entry Decorators**: Composable wrappers for cross-cutting concerns

## Migration Path

To migrate from manual route matching to `NavDisplay`:

1. Create entry provider functions for each navigation domain (e.g., `mainEntryProvider`, `componentsEntryProvider`)
2. Combine them in a top-level `paletteEntryProvider` function
3. Replace manual `when()` statements with `NavDisplay` + `entryProvider`
4. Pass `navState.backStack` directly to `NavDisplay`

The existing API remains backwards compatible, so you can migrate incrementally.

## Dependencies

The navigation library uses:
- `androidx.navigation3:navigation3-runtime` (v1.1.0-alpha03) - Core navigation3 runtime
- `org.jetbrains.androidx.navigation3:navigation3-ui` (v1.0.0-alpha06) - Compose Multiplatform UI components

Both are multiplatform and work across Android, iOS, Desktop, and Web platforms.

## Type Parameter Guidelines

- Always specify `NavController<NavKey>` instead of just `NavController`
- Import `com.alexrdclement.palette.navigation.NavKey` in files using `NavController<NavKey>`
- Pass `navController` and other dependencies explicitly (avoid CompositionLocals for navigation)

## Example: Adding a New Route

1. Define the route:
```kotlin
@Serializable
@SerialName("my-feature")
data object MyFeatureRoute : NavKey {
    override val pathSegment = "my-feature".toPathSegment()
}
```

2. Register in SerializersModule:
```kotlin
val myFeatureSerializersModule = SerializersModule {
    polymorphic(NavKey::class) {
        subclass(MyFeatureRoute::class)
    }
}
```

3. Create entry provider (if using NavDisplay):
```kotlin
fun EntryProviderScope<NavKey>.myFeatureEntryProvider(
    navController: NavController<NavKey>,
) {
    entry<MyFeatureRoute> {
        MyFeatureScreen(navController = navController)
    }
}
```

4. Add to navigation graph:
```kotlin
navGraph(root = AppRoot, start = MainRoute) {
    route(MyFeatureRoute)
}
```

## Further Reading

- [Navigation 3 in Compose Multiplatform](https://kotlinlang.org/docs/multiplatform/compose-navigation-3.html)
- [Modularize navigation code](https://developer.android.com/guide/navigation/navigation-3/modularize)
- [navigation3 Release Notes](https://developer.android.com/jetpack/androidx/releases/navigation3)
