package com.albertsons.employeeapp.dependencyinjection

import com.albertsons.employeeapp.data.remote.APIServices
import com.albertsons.employeeapp.data.repositoryimpl.EmployeesRepositoryImpl
import com.albertsons.employeeapp.domain.repository.EmployeesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppRepositoryModule {

    @Provides
    @Singleton
    fun provideEmployeesRepository(apiServices: APIServices): EmployeesRepository {
        return EmployeesRepositoryImpl(apiServices)
    }

}