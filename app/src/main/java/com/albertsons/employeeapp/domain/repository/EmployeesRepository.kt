package com.albertsons.employeeapp.domain.repository

import com.albertsons.employeeapp.data.responses.EmployeesResponse

interface EmployeesRepository {
    suspend fun getEmployeesList(result: Int) : Result<EmployeesResponse>
}