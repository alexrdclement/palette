package com.alexrdclement.palette.theme.semantic

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.alexrdclement.palette.theme.primitive.FontFamily
import com.alexrdclement.palette.theme.primitive.Typography as PrimitiveTypography
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SemanticTypographyTest {

    private val monospace = PrimitiveTypography(fontFamily = FontFamily.Monospace)
    private val serif = PrimitiveTypography(fontFamily = FontFamily.Serif)
    private val override = TextStyle(fontSize = 99.sp)

    @Test
    fun emptyOverridesResolvesToBase() {
        assertEquals(
            makePaletteTypography(monospace),
            SemanticTypography().resolve(monospace),
        )
    }

    @Test
    fun overridePinsTokenAndLeavesOthersAtBase() {
        val base = makePaletteTypography(monospace)
        val resolved = SemanticTypography()
            .withOverride(TypographyToken.TitleLarge, override)
            .resolve(monospace)

        assertEquals(override, resolved.titleLarge)
        assertEquals(base.display, resolved.display)
    }

    @Test
    fun primitiveChangePropagatesToNonOverriddenTokensOnly() {
        val semantic = SemanticTypography().withOverride(TypographyToken.TitleLarge, override)
        val fromMono = semantic.resolve(monospace)
        val fromSerif = semantic.resolve(serif)

        // An overridden token stays pinned across a primitive change.
        assertEquals(override, fromMono.titleLarge)
        assertEquals(override, fromSerif.titleLarge)
        // A non-overridden token follows the primitive font family.
        assertNotEquals(fromMono.display, fromSerif.display)
    }

    @Test
    fun withOverrideAccumulatesOverrides() {
        val semantic = SemanticTypography()
            .withOverride(TypographyToken.TitleLarge, override)
            .withOverride(TypographyToken.Display, override)

        assertEquals(2, semantic.overrides.size)
    }
}
