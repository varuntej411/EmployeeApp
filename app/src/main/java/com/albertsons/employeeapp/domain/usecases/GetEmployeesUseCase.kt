package com.albertsons.employeeapp.domain.usecases

import com.albertsons.employeeapp.data.responses.EmployeesResponse
import com.albertsons.employeeapp.domain.repository.EmployeesRepository
import com.albertsons.employeeapp.utils.APIDataStatus
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(private val employeesRepository: EmployeesRepository) {

    operator fun invoke(
        result: Int,
    ): Flow<APIDataStatus<EmployeesResponse>> = flow {

            try {
                emit(APIDataStatus.LOADING())
                delay(3000L)
                val employeesResponseResult = employeesRepository.getEmployeesList(result = result)
                if (employeesResponseResult.isSuccess) {
                    emit(APIDataStatus.SUCCESS(data = employeesResponseResult.getOrThrow()))
                } else {
                    emit(
                        APIDataStatus.ERROR(employeesResponseResult.exceptionOrNull()?.localizedMessage
                            ?: "An Unexpected Error Occurred"
                        )
                    )
                }
            } catch (e: HttpException) {
                emit(APIDataStatus.ERROR(e.localizedMessage ?: "An Unexpected Error Occurred"))
            } catch (e: IOException) {
                emit(
                    APIDataStatus.ERROR(
                        e.message ?: "Couldn't reach server, Check Your Internet Connection"
                    )
                )
            }
    }.flowOn(IO)
}