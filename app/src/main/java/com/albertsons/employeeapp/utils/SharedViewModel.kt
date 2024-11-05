package com.albertsons.employeeapp.utils

import androidx.lifecycle.ViewModel
import com.albertsons.employeeapp.data.responses.User

class SharedViewModel : ViewModel() {
    var user: User? = null
}