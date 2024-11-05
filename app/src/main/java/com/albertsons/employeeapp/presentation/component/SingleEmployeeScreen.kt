package com.albertsons.employeeapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.albertsons.employeeapp.data.responses.Dob
import com.albertsons.employeeapp.data.responses.Location
import com.albertsons.employeeapp.data.responses.Name
import com.albertsons.employeeapp.data.responses.Picture
import com.albertsons.employeeapp.data.responses.Street
import com.albertsons.employeeapp.data.responses.User
import com.albertsons.employeeapp.presentation.nav_graph.Route
import com.albertsons.employeeapp.utils.LoadCircularImage
import com.albertsons.employeeapp.utils.MultiPreviewWindow
import com.albertsons.employeeapp.utils.SharedViewModel

@Composable
fun SingleEmployeeScreen(
    result: User,
    navController: NavHostController,
    sharedViewModel: SharedViewModel = viewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White,
            contentColor = Color.Red,
            disabledContentColor = Color.Blue,
            disabledContainerColor = Color.Green
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                ) {
                    LoadCircularImage(
                        imageUrl = result.picture.thumbnail
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    val fullName =
                        result.name.title + ". " + result.name.first + " " + result.name.last
                    Text(
                        text = fullName,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.headlineLarge,
                        maxLines = 2,
                        minLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = result.location.city + ", " + result.location.country,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        textAlign = TextAlign.Start,
                        maxLines = 2,
                        minLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis
                    )

                }

                IconButton(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp),
                    onClick = {
                        sharedViewModel.user = result
                        navController.navigate(Route.MoreDetailsScreen.screen)
                    },
                    enabled = true,
                    colors = IconButtonDefaults.filledIconButtonColors()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "arrow",
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
        }

    }
}

@Composable
@MultiPreviewWindow
fun PreviewSingleEmployeeScreen() {
    SingleEmployeeScreen(
        result = User(
            name = Name(
                first = "varun",
                last = "tej",
                title = "Software Engineer"
            ),
            gender = "male",
            location = Location(
                city = "hyderabad",
                country = "india",
                state = "telangana",
                street = Street(
                    name = "hitech city",
                    number = 2
                ),
            ),
            email = "",
            dob = Dob("25/05/1994", 20),
            phone = "",
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/35.jpg",
                medium = "https://randomuser.me/api/portraits/med/women/35.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/women/35.jpg"
            ),
            city = "hitech City",
            state = "Telangana",
            country = "India",
            postcode = "500081"
        ),
        navController = rememberNavController()
    )
}