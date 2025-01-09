package com.app.graphqltemplate.presentation.continentDetails

import src.main.qraphql.FetchDetailsQuery

sealed interface ContinentDetailsScreenUiState {
    data object Initial : ContinentDetailsScreenUiState
    data object Loading : ContinentDetailsScreenUiState
    data class Success(val obj: FetchDetailsQuery.Data) : ContinentDetailsScreenUiState
    data class Error(val msg: String) : ContinentDetailsScreenUiState
}