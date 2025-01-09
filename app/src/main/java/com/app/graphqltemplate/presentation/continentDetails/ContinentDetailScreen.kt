package com.app.graphqltemplate.presentation.continentDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.graphqltemplate.presentation.components.ErrorState
import com.app.graphqltemplate.presentation.components.Loader

@Composable
fun ContinentDetailScreen(
    modifier: Modifier = Modifier,
    continentDetailsVM: ContinentDetailsVM
) {
    val uiState by continentDetailsVM.continentDetailsResponse.collectAsStateWithLifecycle()

    when (val currentState = uiState) {
        is ContinentDetailsScreenUiState.Error -> ErrorState(
            message = currentState.msg,
            modifier = Modifier.fillMaxSize()
        )

        ContinentDetailsScreenUiState.Initial -> Unit
        ContinentDetailsScreenUiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
        is ContinentDetailsScreenUiState.Success -> ContinentDetailScreenData(currentState)
    }
}

@Composable
fun ContinentDetailScreenData(currentState: ContinentDetailsScreenUiState.Success) {
    currentState.obj.continent?.let { data ->
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = data.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(12.dp)
            )
            data.countries.let {
                it.forEach {
                    Text(text = it.name, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}