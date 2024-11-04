package com.albertsons.employeeapp.presentation.ui_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.albertsons.employeeapp.presentation.EmployeesListViewModel
import com.albertsons.employeeapp.presentation.component.SingleEmployeeScreen
import com.albertsons.employeeapp.utils.MultiPreviewWindow
import com.albertsons.employeeapp.utils.UiText

@Composable
fun HomeScreen(
    navController: NavHostController,
    innerPaddingValues: PaddingValues,
    viewModel: EmployeesListViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiStates.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues),
        contentColor = Color.White
    ) {

        Column() {

        }

        if (uiState.value.isLoading) {
            CustomProgressBar()
        }

        if (uiState.value.error !is UiText.Idle) {
            Box(
                modifier = Modifier
                    .padding(innerPaddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.value.error.getString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPaddingValues)
                )
            }
        }

        uiState.value.data?.let { list ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(list.results!!.size) {
                    list.results.forEach {
                        SingleEmployeeScreen(result = it, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomProgressBar() {
    Box(
        modifier = Modifier.width(10.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@MultiPreviewWindow
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController(), innerPaddingValues = PaddingValues())
}