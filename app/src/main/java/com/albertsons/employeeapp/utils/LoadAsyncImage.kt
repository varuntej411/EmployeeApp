package com.albertsons.employeeapp.utils

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.albertsons.employeeapp.R

@Composable
fun LoadAsyncImage(imageUrl: String, context: Context) {
    AsyncImage(
        // without context we can use model directly assign Any type that can load image otherwise use builder
        //   model = imageUrl,
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .crossfade(true)
            .build(),
        contentDescription = "ImageHolder",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
        // placeholder = painterResource(id = R.drawable.ic_launcher_background), 
        // .border(width = 2.dp, color = Pink40)

        // Use Image Loader instead of model both will work same load type
        // imageLoader = ImageLoader(context).newBuilder()
        // .error(R.drawable.profile)
        //.crossfade(true).placeholder(R.drawable.ic_launcher_background).build(),

        // we can use this state of remeberpainter too based on requirement
        //val painter = rememberImagePainter(data = "https://images.unsplash.com/photo",
        // builder = {
        // transformations(
        // GrayscaleTransformation(),       // Gray Scale Transformation
        // CircleCropTransformation()       // Circle Crop Transformation
        // )
        // })

        //Image(
        //    painter = painter,
        //    contentDescription = "Forest Image",
        //    modifier = Modifier.fillMaxSize(),
        //    contentScale = ContentScale.Crop
        //)

    )
}

@Composable
fun LoadCircularImage(imageUrl: String) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .clip(shape = CircleShape),

        model = imageUrl,
        contentDescription = "image",
        loading = { CustomProgressBarForAsyncImage() },
        error = { R.drawable.ic_launcher_background },
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CustomProgressBarForAsyncImage() {
    Box(
        modifier = Modifier.width(10.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun RectangularImage(imageUrl: String, context: Context) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(5.dp)),

        model = ImageRequest.Builder(context).data(imageUrl).build(),
        contentDescription = "image",
        loading = { CustomProgressBarForAsyncImage() },
        error = { R.drawable.ic_launcher_background },
        contentScale = ContentScale.FillBounds
    )
}

@MultiPreviewWindow
@Composable
fun PreviewAsyncImage() {
    LoadAsyncImage(imageUrl = "https://i.pravatar.cc", context = LocalContext.current)
}