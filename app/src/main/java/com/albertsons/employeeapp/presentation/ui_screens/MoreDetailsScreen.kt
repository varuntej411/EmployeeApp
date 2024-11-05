package com.albertsons.employeeapp.presentation.ui_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.albertsons.employeeapp.data.responses.Dob
import com.albertsons.employeeapp.data.responses.Location
import com.albertsons.employeeapp.data.responses.Name
import com.albertsons.employeeapp.data.responses.Picture
import com.albertsons.employeeapp.data.responses.Street
import com.albertsons.employeeapp.data.responses.User
import com.albertsons.employeeapp.utils.MultiPreviewWindow
import com.albertsons.employeeapp.utils.RectangularImage

@Composable
fun MoreDetailsScreen(
    navController: NavHostController,
    contentPaddingValues: PaddingValues,
    user: User?
) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPaddingValues),
        contentColor = Color.White
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(4.dp),
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
                val name = user?.name?.first + " " + user?.name?.last
                Text(
                    text = name,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                RectangularImage(
                    imageUrl = user?.picture?.medium.toString(),
                    context = context
                )
            }

            val fullName = user?.name?.title + ". " + user?.name?.first + " " + user?.name?.last
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
                text = "Age: ${user?.dob?.age}",
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
                text = "Gender: ${user?.gender}",
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
        contentPaddingValues = PaddingValues(),
        user = User(
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
        )
    )
}