package com.example.medoblock

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medoblock.features.Screens
import com.example.medoblock.features.chat.ChatViewModel
import com.example.medoblock.features.ui.theme.MedoBlockTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ////WindowCompat.setDecorFitsSystemWindows(window, false)
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        setContent {
            BuildScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun BuildScreen() {
    val isDynamicColor = remember { mutableStateOf(false) }

    MedoBlockTheme(
        dynamicColor = isDynamicColor.value
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberAnimatedNavController()
            val chatViewModel = hiltViewModel<ChatViewModel>()

            Box{
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screens.MainScreen.route,
                    builder = {
                        mainScreen(navController)
                        chat(navController, chatViewModel)
                        medicines(navController)
                        supplyChainDetails(navController)
                    }
                )
            }
        }
    }
}
