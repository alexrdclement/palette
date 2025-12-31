package com.alexrdclement.palette.app.configuration

import androidx.compose.runtime.Composable

interface ConfigurationState {
    val colorMode: ColorMode
}

interface ConfigurationController : ConfigurationState {
    fun setColorMode(colorMode: ColorMode): Boolean
}

@Composable
expect fun rememberConfigurationController(): ConfigurationController
