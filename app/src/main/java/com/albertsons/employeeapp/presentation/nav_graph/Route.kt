package com.albertsons.employeeapp.presentation.nav_graph

sealed class Route(val screen: String) {
    data object HomeScreen: Route(screen = "home_screen")
    data object MoreDetailsScreen: Route(screen = "moredetails_screen")
}