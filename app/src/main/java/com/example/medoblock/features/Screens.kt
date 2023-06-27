package com.example.medoblock.features

sealed class Screens (
    val route: String,
    val name: String
){
    object MainScreen: Screens("main-screen", "Home")
    object SupplyChainDetails: Screens("supply-chain-details", "Supply Chain")
    object Medicines: Screens("medicines", "Medicines")
    object Chat: Screens("chat", "Chat")

    companion object{
        val screenList by lazy {
            listOf(
                MainScreen,
                SupplyChainDetails,
                Medicines,
                Chat
            )
        }
    }
}