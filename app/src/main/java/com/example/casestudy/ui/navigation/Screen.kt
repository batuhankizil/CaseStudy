package com.example.casestudy.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object CreateRestaurant : Screen("create_restaurant")
    object RestaurantDetail : Screen("restaurant_detail")
    object OrderList : Screen("order_list")
    object Map : Screen("map")
    object Dashboard : Screen("dashboard")
}
