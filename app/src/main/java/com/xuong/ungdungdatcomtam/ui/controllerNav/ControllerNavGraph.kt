package com.xuong.ungdungdatcomtam.ui.controllerNav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xuong.ungdungdatcomtam.ui.bottomNav.BottomMainScreen
import com.xuong.ungdungdatcomtam.ui.screens.BoardingScreen
import com.xuong.ungdungdatcomtam.ui.screens.LoginScreen
import com.xuong.ungdungdatcomtam.ui.screens.SignUpScreen

@Composable
fun ControllerNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.BOARDING_SCREEN.route) {
        composable(Screen.BOARDING_SCREEN.route) {
            BoardingScreen(navController)
        }
        composable(Screen.LOGIN_SCREEN.route) {
            LoginScreen(navController)
        }
        composable(Screen.SIGN_UP_SCREEN.route) {
            SignUpScreen(navController)
        }
        composable(Screen.BOTTOM_MAIN_SCREEN.route) {
            BottomMainScreen(navController)
        }
    }
}