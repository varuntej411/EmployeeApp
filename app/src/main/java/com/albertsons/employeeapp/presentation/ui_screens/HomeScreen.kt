package com.albertsons.employeeapp.presentation.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.albertsons.employeeapp.presentation.EmployeesList.Event.GetEmployeesList
import com.albertsons.employeeapp.presentation.EmployeesListViewModel
import com.albertsons.employeeapp.presentation.component.SingleEmployeeScreen
import com.albertsons.employeeapp.presentation.nav_graph.Route
import com.albertsons.employeeapp.utils.MultiPreviewWindow
import com.albertsons.employeeapp.utils.SharedViewModel
import com.albertsons.employeeapp.utils.UiText

@Composable
fun HomeScreen(
    navController: NavHostController,
    innerPaddingValues: PaddingValues,
    viewModel: EmployeesListViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = viewModel()
) {

    var searchField by remember {
        mutableStateOf("")
    }

    val isVisible by remember {
        derivedStateOf {
            searchField.isNotBlank()
        }
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues),
        contentColor = Color.White
    ) {
        val uiState = viewModel.uiStates.collectAsState().value

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(10.dp)
                    .background(color = Color.Black, shape = RoundedCornerShape(10.dp)),
                value = searchField,
                onValueChange = { searchField = it },
                label = {
                    Text(
                        text = "Search User Count",
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.LightGray,
                    errorContainerColor = Color.White,
                    errorLeadingIconColor = Color.Red,
                    errorLabelColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Search,
                ),
                trailingIcon = {
                    if (isVisible) {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(GetEmployeesList(searchField.toInt()))
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = ""
                            )
                        }
                    }
                },
                isError = true,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))


            if (uiState.isLoading) {
                CustomProgressBar()
            }

            if (uiState.error !is UiText.Idle) {
                Box(
                    modifier = Modifier
                        .padding(innerPaddingValues)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error.getString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPaddingValues)
                    )
                }
            }

            uiState.data?.let { list ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(list.results?.size!!) {
                        SingleEmployeeScreen(
                            result = list.results[it],
                            navController = navController,
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
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