package com.albertsons.employeeapp.presentation.nav_graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.albertsons.employeeapp.presentation.EmployeesList
import com.albertsons.employeeapp.presentation.EmployeesListViewModel
import com.albertsons.employeeapp.presentation.ui_screens.HomeScreen
import com.albertsons.employeeapp.presentation.ui_screens.MoreDetailsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(navController = navController, startDestination = Route.HomeScreen.screen) {
        composable(route = Route.HomeScreen.screen) {
         val employeesListViewModel = hiltViewModel<EmployeesListViewModel>()
            val title = it.arguments?.getInt("title")
            LaunchedEffect(key1 = title) {
                title?.let {
                    employeesListViewModel.onEvent(EmployeesList.Event.GetEmployeesList(it))
                }
            }
            HomeScreen(navController = navController, innerPaddingValues = innerPadding)
        }

        composable(route = Route.MoreDetailsScreen.screen) {
            val employeesListViewModel = hiltViewModel<EmployeesListViewModel>()
            val title = it.arguments?.getString("title")
            LaunchedEffect(key1 = title) {
                title?.let {
                    //employeesListViewModel.onEvent(EmployeesList.Event.FetchAllMovies(it))
                }
            }
            MoreDetailsScreen(
                navController = navController,
                contentPaddingValues = innerPadding
            )
        }
    }
}