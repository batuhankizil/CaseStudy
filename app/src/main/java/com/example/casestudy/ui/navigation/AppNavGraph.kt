package com.example.casestudy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.casestudy.ui.auth.LoginScreen
import com.example.casestudy.ui.auth.RegisterScreen
import com.example.casestudy.ui.map.MapScreen
import com.example.casestudy.ui.order.OrderListScreen
import com.example.casestudy.ui.restaurant.RestaurantDetailScreen
import com.example.casestudy.ui.dashboard.DashboardScreen
import com.example.casestudy.ui.restaurant.CreateRestaurantScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.OrderList.route) {
            OrderListScreen(navController = navController)
        }
        composable(Screen.CreateRestaurant.route) {
            CreateRestaurantScreen(navController = navController)
        }
        composable(Screen.RestaurantDetail.route) {
            RestaurantDetailScreen(navController = navController)
        }
        composable(Screen.Map.route) {
            MapScreen(navController = navController)
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
    }
}
