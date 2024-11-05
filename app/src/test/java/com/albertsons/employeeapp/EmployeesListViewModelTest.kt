package com.albertsons.employeeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.albertsons.employeeapp.domain.repository.EmployeesRepository
import com.albertsons.employeeapp.domain.usecases.GetEmployeesUseCase
import com.albertsons.employeeapp.presentation.EmployeesList
import com.albertsons.employeeapp.presentation.EmployeesListViewModel
import com.albertsons.employeeapp.utils.APIDataStatus.ERROR
import com.albertsons.employeeapp.utils.UiText
import com.albertsons.employeeapp.utils.UserUtils
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class EmployeesListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userViewModel: EmployeesListViewModel
    private val employeesRepository: EmployeesRepository = mockk()
    private val getEmployeesUseCase: GetEmployeesUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        userViewModel = EmployeesListViewModel(
            getEmployeesUseCase = getEmployeesUseCase,
            employeesRepository = employeesRepository
        )
    }

    @Test
    fun `fetchUsers should return success state when users are fetched successfully`() = runTest {
        // Given
        val userList = UserUtils.createSampleUserList()
        coEvery { employeesRepository.getEmployeesList(2) } returns userList.results

        // When
        userViewModel.getEmployeesList(2)
        advanceUntilIdle()
        // Then
        userViewModel.uiStates.value.data?.results?.first() { state ->
            assert(state is userViewModel.uiStates.value)
            assert((state as userViewModel.uiStates.value.Success).users == userList)
        }
    }

    @Test
    fun `fetchUsers should return error state when fetching users fails`() = runTest {
        // Given
        val errorMessage = "Network Error"
        val errorState = ERROR(message = errorMessage)
        coEvery { employeesRepository.getEmployeesList(2) } throws Exception("Network error")

        // When
        userViewModel.getEmployeesList(errorState) // Simulating the error state update

        // Then
        assertEquals(false, userViewModel.uiStates.value.isLoading)
        assertEquals(null, userViewModel.uiStates.value.data)
        assertEquals(UiText.RemoteString(errorMessage), userViewModel.uiStates.value.error)
    }

    @Test
    fun `fetchUsers should show loading state before fetching users`() = runTest {
        // Given
        coEvery { employeesRepository.getEmployeesList(2) } returns emptyList() // Adjust as needed

        // When
        userViewModel.getEmployeesList(2)


        // Then
        val state = userViewModel.uiStates.value
        assert(state is EmployeesList.UiState)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain() // Reset Main dispatcher
    }
}
