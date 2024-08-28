package br.com.amigosdasorte.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.amigosdasorte.util.BottomBarScreen

@Composable
fun BottomBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Groups.route
    ) {
        composable(route = BottomBarScreen.Groups.route){
            HomeScreen()
        }

        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(
                accountViewModel = viewModel()
            )
        }
    }
}