package com.xuong.ungdungdatcomtam.ui.controllerNav

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xuong.ungdungdatcomtam.ui.bottomNav.BottomMainScreen
import com.xuong.ungdungdatcomtam.ui.screens.BoardingScreen
import com.xuong.ungdungdatcomtam.ui.screens.FoodMaganeScreen
import com.xuong.ungdungdatcomtam.ui.screens.LoginScreen
import com.xuong.ungdungdatcomtam.ui.screens.ManageScreen
import com.xuong.ungdungdatcomtam.ui.screens.SignUpScreen

@Composable
fun SetStatusBarColor(color: Color) {
    val view = LocalView.current
    if (view.isInEditMode.not()) {
        val window = (view.context as Activity).window
        window.statusBarColor = color.toArgb()

        val wic = WindowInsetsControllerCompat(window, view)
        wic.isAppearanceLightStatusBars = false
    }
}

@Composable
fun ControllerNavGraph() {
    SetStatusBarColor(Color.Black)

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
            BottomMainScreen()
        }
        composable(Screen.BOTTOM_MAIN_SCREEN.route) {
            BottomMainScreen()
        }
        composable(Screen.FOOD_MANAGE_SCREEN.route) {
            FoodMaganeScreen(navController)
        }

    }
}