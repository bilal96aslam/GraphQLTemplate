package com.app.graphqltemplate.presentation.continents

import src.main.qraphql.ContinentFetchingQuery

sealed interface ContinentsScreenUiState {
    data object Initial : ContinentsScreenUiState
    data object Loading : ContinentsScreenUiState
    data class Success(val obj: ContinentFetchingQuery.Data) : ContinentsScreenUiState
    data class Error(val msg: String) : ContinentsScreenUiState
}
