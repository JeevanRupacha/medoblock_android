package com.example.medoblock

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.navigation.*
import com.example.medoblock.features.Screens
import com.example.medoblock.features.supplyChainDetails.SupplyChainDetailsScreen
import com.example.medoblock.features.chat.ChatScreen
import com.example.medoblock.features.chat.ChatViewModel
import com.example.medoblock.features.main.MainScreen
import com.example.medoblock.features.medicines.MedicinesScreen
import com.google.accompanist.navigation.animation.composable


fun enterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { 300 },
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        )
    ) + fadeIn(animationSpec = tween(600))
}

fun exitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -300 },
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(600))
}

fun popEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -300 },
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        )
    ) + fadeIn(animationSpec = tween(600))
}


fun popExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { 300 },
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(600))
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainScreen(
    navController: NavController
) {
    val route = Screens.MainScreen.route
    composable(
        route = route,
        enterTransition = { enterTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
        exitTransition = { exitTransition() }
    ) {
        MainScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.chat(
    navController: NavController,
    viewModel: ChatViewModel
) {
    val route = Screens.Chat.route
    composable(
        route = route,
        enterTransition = { enterTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
        exitTransition = { exitTransition() }
    ) {
        ChatScreen(navController = navController, viewModel)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.medicines(
    navController: NavController
) {
    val route = Screens.Medicines.route
    composable(
        route = route,
        enterTransition = { enterTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
        exitTransition = { exitTransition() }
    ) {
        MedicinesScreen(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.supplyChainDetails(
    navController: NavController
) {
    val route = Screens.SupplyChainDetails.route
    composable(
        route = route,
        enterTransition = { enterTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
        exitTransition = { exitTransition() }
    ) {
        SupplyChainDetailsScreen(navController = navController)
    }
}



fun NavController.goToMain(){
    val route = Screens.MainScreen.route
    this.navigate(route){ launchSingleTop = true }
}

fun NavController.goToChat(){
    val route = Screens.Chat.route
    this.navigate(route){ launchSingleTop = true }
}

fun NavController.goToMedicines(){
    val route = Screens.Medicines.route
    this.navigate(route){ launchSingleTop = true }
}

fun NavController.goToSupplyChainDetails(){
    val route = Screens.SupplyChainDetails.route
    this.navigate(route){ launchSingleTop = true }
}