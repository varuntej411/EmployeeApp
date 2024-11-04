package com.albertsons.employeeapp

import com.albertsons.employeeapp.data.responses.Dob
import com.albertsons.employeeapp.data.responses.EmployeesResponse
import com.albertsons.employeeapp.data.responses.Location
import com.albertsons.employeeapp.data.responses.Name
import com.albertsons.employeeapp.data.responses.Picture
import com.albertsons.employeeapp.data.responses.Street
import com.albertsons.employeeapp.data.responses.User
import com.albertsons.employeeapp.domain.usecases.GetEmployeesUseCase
import com.albertsons.employeeapp.presentation.EmployeesList
import com.albertsons.employeeapp.presentation.EmployeesListViewModel
import com.albertsons.employeeapp.utils.APIDataStatus
import com.albertsons.employeeapp.utils.UiText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class EmployeesListViewModelTest {

    private lateinit var viewModel: EmployeesListViewModel

    @Mock
    private lateinit var getEmployeesUseCase: GetEmployeesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = EmployeesListViewModel(getEmployeesUseCase)
    }

    @Test
    fun `test initial state`() {
        // Given
        val initialState = EmployeesList.UiState()

        // When
        val actualState = viewModel.uiStates.value

        // Then
        assertEquals(initialState, actualState)
    }

    @Test
    fun `test loading state`() = runBlocking {
        // Given
        `when`(getEmployeesUseCase.invoke(result = 10)).thenReturn(flowOf(APIDataStatus.LOADING()))

        // When
        viewModel.onEvent(EmployeesList.Event.GetEmployeesList(10))

        // Then
        assertEquals(true, viewModel.uiStates.value.isLoading)
    }

    @Test
    fun `test success state`() = runBlocking {
        // Given
        val mockData = EmployeesResponse(
            listOf(
                User(
                    name = Name(
                        first = "varun",
                        last = "tej",
                        title = "Software Engineer"
                    ),
                    gender = "male",
                    location = Location(
                        city = "hyderabad",
                        country = "india",
                        state = "telangana",
                        street = Street(
                            name = "hitech city",
                            number = 2
                        ),
                    ),
                    email = "",
                    dob = Dob("25/05/1994", 20),
                    phone = "",
                    picture = Picture(
                        large = "https://randomuser.me/api/portraits/women/35.jpg",
                        medium = "https://randomuser.me/api/portraits/med/women/35.jpg",
                        thumbnail = "https://randomuser.me/api/portraits/thumb/women/35.jpg"
                    ),
                    city = "hitech City",
                    state = "Telangana",
                    country = "India",
                    postcode = "500081"
                )
            )
        ) // Mock your EmployeesResponse accordingly

        `when`(getEmployeesUseCase.invoke(result = 10)).thenReturn(
            flowOf(
                APIDataStatus.SUCCESS(
                    mockData
                )
            )
        )

        // When
        viewModel.onEvent(EmployeesList.Event.GetEmployeesList(10))

        // Then
        assertEquals(false, viewModel.uiStates.value.isLoading)
        assertEquals(mockData, viewModel.uiStates.value.data)
        assertEquals(UiText.Idle, viewModel.uiStates.value.error)
    }

    @Test
    fun `test error state`() = runBlocking {
        // Given
        val errorMessage = "Network Error"
        `when`(getEmployeesUseCase.invoke(result = 10)).thenReturn(
            flowOf(
                APIDataStatus.ERROR(
                    errorMessage
                )
            )
        )

        // When
        viewModel.onEvent(EmployeesList.Event.GetEmployeesList(10))

        // Then
        assertEquals(false, viewModel.uiStates.value.isLoading)
        assertEquals(null, viewModel.uiStates.value.data)
        assertEquals(UiText.RemoteString(errorMessage), viewModel.uiStates.value.error)
    }
}
