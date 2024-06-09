package com.xuong.ungdungdatcomtam.ui.bottomNav

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xuong.ungdungdatcomtam.repository.FoodRepository
import com.xuong.ungdungdatcomtam.repository.Repository
import com.xuong.ungdungdatcomtam.room.CategoryDB
import com.xuong.ungdungdatcomtam.room.FoodDB
import com.xuong.ungdungdatcomtam.ui.controllerNav.Screen
import com.xuong.ungdungdatcomtam.ui.screens.CategoryScreen
import com.xuong.ungdungdatcomtam.ui.screens.FoodMaganeScreen
import com.xuong.ungdungdatcomtam.ui.screens.HomeScreen
import com.xuong.ungdungdatcomtam.ui.screens.ManageScreen
import com.xuong.ungdungdatcomtam.ui.screens.ProfileScreen
import com.xuong.ungdungdatcomtam.ui.screens.StatisticScreen
import com.xuong.ungdungdatcomtam.viewmodel.CategoryViewModel
import com.xuong.ungdungdatcomtam.viewmodel.FoodViewModel


@Composable
fun BottomNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val dbCategory = CategoryDB.getInstance(context)
    val repositoryCategory = Repository(dbCategory)
    val categoryViewModel = CategoryViewModel(repositoryCategory)

    val dbFood = FoodDB.getInstance(context)
    val repositoryFood = FoodRepository(dbFood)
    val foodViewModel = FoodViewModel(repositoryFood)

    NavHost(
        navController = navController, startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Statistic.route) {
            StatisticScreen()
        }
        composable(route = BottomBarScreen.Manage.route) {
            ManageScreen(navController)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = Screen.FOOD_MANAGE_SCREEN.route) {
            FoodMaganeScreen(viewModel = foodViewModel, categoryViewModel = categoryViewModel){ navController.popBackStack() }
        }
        composable(route = Screen.CATEGORY_SCREEN.route) {
            CategoryScreen(
                viewModel = categoryViewModel
            ) { navController.popBackStack() }
        }
    }
}
