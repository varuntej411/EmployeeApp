package com.albertsons.employeeapp.presentation.ui_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.albertsons.employeeapp.presentation.EmployeesListViewModel
import com.albertsons.employeeapp.utils.MultiPreviewWindow
import com.albertsons.employeeapp.utils.RectangularImage

@Composable
fun MoreDetailsScreen(
    navController: NavHostController,
    contentPaddingValues: PaddingValues,
    viewModel: EmployeesListViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPaddingValues),
        contentColor = Color.White
    ) {

        val uiState = viewModel.uiStates.collectAsStateWithLifecycle()


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(
                    text = uiState.value.data?.results?.first()?.name?.title.toString(),
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif
                )
            }
            uiState.value.data?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    RectangularImage(
                        imageUrl = "${it.results?.first()?.picture?.medium}",
                        context = context
                    )
                }
            }

            val fullName =
                uiState.value.data?.results?.first()?.name?.title + ". " + uiState.value.data?.results?.first()?.name?.first + " " + uiState.value.data?.results?.first()?.name?.last
            Text(
                text = fullName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                maxLines = 5,
                fontSize = 20.sp,
                letterSpacing = 2.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Age: ${uiState.value.data?.results?.first()?.dob?.age}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                maxLines = 5,
                fontSize = 20.sp,
                letterSpacing = 2.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Gender: ${uiState.value.data?.results?.first()?.gender}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                maxLines = 5,
                fontSize = 20.sp,
                letterSpacing = 2.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        }


    }
}

@MultiPreviewWindow
@Composable
fun PreviewMoreDetailsScreen() {
    MoreDetailsScreen(
        navController = rememberNavController(),
        contentPaddingValues = PaddingValues()
    )
}