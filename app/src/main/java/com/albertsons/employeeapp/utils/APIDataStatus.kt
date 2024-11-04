package com.albertsons.employeeapp.utils

sealed class APIDataStatus<T>(
    val data: T? = null, val message: String? = null
) {
    class LOADING<T>(data: T? = null) : APIDataStatus<T>(data)
    class SUCCESS<T>(data: T?) : APIDataStatus<T>(data)
    class ERROR<T>(message: String, data: T? = null) : APIDataStatus<T>(data, message)
}