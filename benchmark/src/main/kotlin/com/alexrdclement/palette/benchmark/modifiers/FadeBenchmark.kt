package com.alexrdclement.palette.benchmark.modifiers

import androidx.benchmark.macro.BaselineProfileMode
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.TraceSectionMetric
import androidx.benchmark.macro.TraceSectionMetric.Mode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alexrdclement.palette.MainCatalogPage
import com.alexrdclement.palette.appPackageName
import com.alexrdclement.palette.modifiers.FadePage
import com.alexrdclement.palette.modifiers.ModifiersPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FadeBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun compilationModeNone() = lengthAdjustment(CompilationMode.None())

    @Test
    fun compilationModePartial() =
        lengthAdjustment(CompilationMode.Partial(BaselineProfileMode.Require))

    @OptIn(ExperimentalMetricApi::class)
    fun lengthAdjustment(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = appPackageName,
        metrics = listOf(
            FrameTimingMetric(),
            TraceSectionMetric("fade", Mode.Sum),
        ),
        iterations = 5,
        startupMode = StartupMode.WARM,
        compilationMode = compilationMode,
        setupBlock = {
            pressHome()
            startActivityAndWait()

            MainCatalogPage(device).navigateToModifiers()
            ModifiersPage(device).navigateToFade()
        }
    ) {
        FadePage(device).adjustFade()
    }
}
