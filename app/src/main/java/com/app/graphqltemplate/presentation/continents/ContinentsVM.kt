package com.app.graphqltemplate.presentation.continents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.graphqltemplate.data.repoistory.Constant.ERROR_TEXT
import com.app.graphqltemplate.domain.usecase.FetchContinentUseCase
import com.app.graphqltemplate.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import src.main.qraphql.ContinentFetchingQuery
import javax.inject.Inject

@HiltViewModel
class ContinentsVM @Inject constructor(
    private val fetchContinentUseCase: FetchContinentUseCase
) : ViewModel() {

    private val _continentResponse =
        MutableStateFlow<ContinentsScreenUiState>(ContinentsScreenUiState.Initial)
    val continentResponse: StateFlow<ContinentsScreenUiState> = _continentResponse.asStateFlow()

    init {
        getContinents()
    }

    private fun getContinents() = fetchContinentUseCase.invoke()
        .onEach { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Error -> _continentResponse.value = ContinentsScreenUiState.Error(
                    apiResponse.message ?: ERROR_TEXT
                )

                is ApiResponse.Loading -> _continentResponse.value = ContinentsScreenUiState.Loading
                is ApiResponse.Success -> _continentResponse.value =
                    ContinentsScreenUiState.Success(
                        apiResponse.data ?: ContinentFetchingQuery.Data(
                            listOf()
                        )
                    )
            }
        }.launchIn(viewModelScope)

}