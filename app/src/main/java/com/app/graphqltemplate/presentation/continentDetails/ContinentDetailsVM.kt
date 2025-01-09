package com.app.graphqltemplate.presentation.continentDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.graphqltemplate.data.repoistory.Constant.ERROR_TEXT
import com.app.graphqltemplate.domain.usecase.FetchContinentDetailUseCase
import com.app.graphqltemplate.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import src.main.qraphql.FetchDetailsQuery
import javax.inject.Inject

@HiltViewModel
class ContinentDetailsVM @Inject constructor(
    private val continentDetailUseCase: FetchContinentDetailUseCase
) : ViewModel() {

    private val _continentDetailsResponse =
        MutableStateFlow<ContinentDetailsScreenUiState>(ContinentDetailsScreenUiState.Initial)
    val continentDetailsResponse: StateFlow<ContinentDetailsScreenUiState> =
        _continentDetailsResponse.asStateFlow()

    fun getContinentDetails(code: String) = continentDetailUseCase.invoke(code)
        .onEach { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Error -> _continentDetailsResponse.value =
                    ContinentDetailsScreenUiState.Error(
                        apiResponse.message ?: ERROR_TEXT
                    )

                is ApiResponse.Loading -> _continentDetailsResponse.value =
                    ContinentDetailsScreenUiState.Loading

                is ApiResponse.Success -> _continentDetailsResponse.value =
                    ContinentDetailsScreenUiState.Success(
                        apiResponse.data ?: FetchDetailsQuery.Data(
                            null
                        )
                    )
            }

        }.launchIn(viewModelScope)
}