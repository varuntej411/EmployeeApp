package com.albertsons.employeeapp.presentation.nav_graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.albertsons.employeeapp.presentation.ui_screens.HomeScreen
import com.albertsons.employeeapp.presentation.ui_screens.MoreDetailsScreen
import com.albertsons.employeeapp.utils.SharedViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = Route.HomeScreen.screen) {
        composable(route = Route.HomeScreen.screen) {
            HomeScreen(navController = navController, innerPaddingValues = innerPadding, sharedViewModel = sharedViewModel)
        }

        composable(route = Route.MoreDetailsScreen.screen) {
            MoreDetailsScreen(
                navController = navController,
                contentPaddingValues = innerPadding,
                user = sharedViewModel.user
            )
        }
    }
}