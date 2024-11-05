package com.albertsons.employeeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertsons.employeeapp.data.responses.EmployeesResponse
import com.albertsons.employeeapp.domain.repository.EmployeesRepository
import com.albertsons.employeeapp.domain.usecases.GetEmployeesUseCase
import com.albertsons.employeeapp.utils.APIDataStatus
import com.albertsons.employeeapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EmployeesListViewModel @Inject constructor(
    private val getEmployeesUseCase: GetEmployeesUseCase,
    val employeesRepository: EmployeesRepository
) : ViewModel() {

    val _uiStates = MutableStateFlow(EmployeesList.UiState())
    var uiStates: StateFlow<EmployeesList.UiState> = _uiStates.asStateFlow()


   /* init {
        getEmployeesList(10)
    }
*/
    fun onEvent(event: EmployeesList.Event) {
        when (event) {
            is EmployeesList.Event.GetEmployeesList -> {
                getEmployeesList(event.results)
            }
        }
    }

    fun getEmployeesList(results: Int) = getEmployeesUseCase.invoke(result = results).onEach { result ->

        when (result) {
            is APIDataStatus.LOADING -> {
                _uiStates.update {
                    EmployeesList.UiState(isLoading = true)
                }
            }

            is APIDataStatus.SUCCESS -> {
                _uiStates.update {
                    EmployeesList.UiState(data = result.data)
                }
            }

            is APIDataStatus.ERROR -> {
                _uiStates.update {
                    EmployeesList.UiState(
                        error = UiText.RemoteString(result.message ?: "An Unexpected Error Occurred")
                    )
                }
            }
        }
    }.launchIn(viewModelScope)
}

object EmployeesList {

    data class UiState(
        val isLoading: Boolean = false,
        val data: EmployeesResponse? = null,
        val error: UiText = UiText.Idle,
    )

    sealed interface Navigation {
        data object GoToEmployeesDetailsScreen : Navigation
    }

    sealed interface Event {
        data class GetEmployeesList(val results: Int) : Event
    }

}