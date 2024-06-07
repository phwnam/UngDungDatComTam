package com.xuong.ungdungdatcomtam.ui.bottomNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xuong.ungdungdatcomtam.ui.screens.HomeScreen
import com.xuong.ungdungdatcomtam.ui.screens.ManageScreen
import com.xuong.ungdungdatcomtam.ui.screens.ProfileScreen
import com.xuong.ungdungdatcomtam.ui.screens.StatisticScreen


@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Statistic.route){
            StatisticScreen()
        }
        composable(route = BottomBarScreen.Manage.route){
            ManageScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}