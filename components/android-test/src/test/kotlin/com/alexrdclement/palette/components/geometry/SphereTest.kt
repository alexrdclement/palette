package com.alexrdclement.palette.components.geometry

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.util.ViewingAngle
import com.alexrdclement.palette.testing.PaparazziTestRule
import com.alexrdclement.palette.theme.PaletteTheme
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class SphereTest {
    @get:Rule
    val paparazzi = PaparazziTestRule

    @Test
    fun gridSphere() {
        paparazzi.snapshot {
            PaletteTheme {
                Surface {
                    GridSphere(
                        numLatitudeLines = 20,
                        numLongitudeLines = 10,
                        modifier = Modifier.size(200.dp),
                        viewingAngle = ViewingAngle(
                            rotationX = 20f,
                        ),
                    )
                }
            }
        }
    }
}

