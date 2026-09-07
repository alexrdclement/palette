package com.alexrdclement.palette

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.alexrdclement.palette.app.App
import com.alexrdclement.palette.app.navigation.rememberPaletteNavController
import com.alexrdclement.palette.navigation.NavController

class MainActivity : ComponentActivity() {
    private var navController: NavController? = null

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberPaletteNavController(
                initialDeeplink = intent?.getDeeplink(),
                buildSyntheticBackStack = intent.isNewTask,
                onBackStackEmpty = ::finish,
            ).also { navController = it }

            // Expose Compose testTags as resource ids so UI Automator fixtures (e.g. baseline
            // profile generation) can locate tagged nodes such as catalog items generically.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics { testTagsAsResourceId = true }
            ) {
                App(
                    navController = navController,
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        intent.getDeeplink()?.let { deeplink ->
            navController?.navigateToDeeplink(deeplink, replace = false)
        }
    }

    private fun Intent.getDeeplink(): String? {
        return data?.let { uri ->
            uri.path?.removePrefix("/") ?: ""
        }
    }

    private val Intent?.isNewTask: Boolean
        get() = (this?.flags ?: 0) and Intent.FLAG_ACTIVITY_NEW_TASK != 0
}
