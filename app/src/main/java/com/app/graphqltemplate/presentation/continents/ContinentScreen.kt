package com.app.graphqltemplate.presentation.continents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.app.graphqltemplate.R
import com.app.graphqltemplate.presentation.components.AppToolBar
import com.app.graphqltemplate.presentation.components.ErrorState
import com.app.graphqltemplate.presentation.components.Loader

@Composable
fun ContinentScreen(
    continentsVM: ContinentsVM,
    navController: NavHostController,
    onClick: (String) -> Unit
) {

    val uiState by continentsVM.continentResponse.collectAsStateWithLifecycle()

    when (val currentUiState = uiState) {
        is ContinentsScreenUiState.Error -> ErrorState(
            message = currentUiState.msg,
            modifier = Modifier.fillMaxSize()
        )

        ContinentsScreenUiState.Initial -> Unit
        ContinentsScreenUiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
        is ContinentsScreenUiState.Success -> ShowContinentsList(
            currentUiState,
            onClick,
            navController
        )
    }
}

@Composable
fun ShowContinentsList(
    currentUiState: ContinentsScreenUiState.Success,
    onClick: (String) -> Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppToolBar(
            navController = navController,
            title = stringResource(R.string.continents)
        )

        currentUiState.obj.continents.let { list ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(list) {
                    Card(
                        modifier = Modifier
                            .clickable { onClick.invoke(it.code) }
                            .padding(10.dp)
                            .fillMaxWidth(),

                        ) {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

            }
        }
    }
}