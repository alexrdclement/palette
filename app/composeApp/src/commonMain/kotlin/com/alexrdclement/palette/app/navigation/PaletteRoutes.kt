package com.alexrdclement.palette.app.navigation

import com.alexrdclement.palette.app.demo.components.navigation.componentsSerializersModule
import com.alexrdclement.palette.app.demo.formats.navigation.formatsSerializersModule
import com.alexrdclement.palette.app.demo.modifiers.navigation.modifiersSerializersModule
import com.alexrdclement.palette.app.main.navigation.mainSerializersModule
import com.alexrdclement.palette.app.theme.navigation.themeSerializersModule
import com.alexrdclement.palette.navigation.navKeySerializersModule
import kotlinx.serialization.json.Json

val paletteSerializersModule = navKeySerializersModule {
    subclass<PaletteGraph>()
    include(mainSerializersModule)
    include(componentsSerializersModule)
    include(formatsSerializersModule)
    include(modifiersSerializersModule)
    include(themeSerializersModule)
}

val PaletteNavKeyJson = Json {
    serializersModule = paletteSerializersModule
    prettyPrint = false
    ignoreUnknownKeys = true
    // Use @SerialName annotations for discriminator values
    classDiscriminator = "type"
}
