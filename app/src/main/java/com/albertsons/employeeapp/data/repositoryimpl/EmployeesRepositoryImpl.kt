package com.albertsons.employeeapp.data.repositoryimpl

import com.albertsons.employeeapp.data.remote.APIServices
import com.albertsons.employeeapp.data.responses.EmployeesResponse
import com.albertsons.employeeapp.domain.repository.EmployeesRepository
import javax.inject.Inject

class EmployeesRepositoryImpl @Inject constructor(
    private val apiServices: APIServices
) : EmployeesRepository {

    override suspend fun getEmployeesList(result: Int): Result<EmployeesResponse> {
        return try {
            val response = apiServices.getEmployeesList(result = result)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: run {
                    Result.failure(Exception("Error Occurred"))
                }
            } else {
                Result.failure(Exception("Error Occurred"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}