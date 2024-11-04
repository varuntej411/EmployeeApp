package com.albertsons.employeeapp.data.remote

import com.albertsons.employeeapp.data.responses.EmployeesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    @GET("api/")
    suspend fun getEmployeesList(
        @Query("results") result: String
    ): Response<EmployeesResponse>

}