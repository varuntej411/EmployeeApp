package com.albertsons.employeeapp.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "LightTheme",
    device = Devices.PIXEL_2,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "DarkTheme",
    device = Devices.PIXEL,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class MultiPreviewWindow