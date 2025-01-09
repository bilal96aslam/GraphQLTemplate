package com.app.graphqltemplate.presentation.continentDetails

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.app.graphqltemplate.R
import com.app.graphqltemplate.presentation.components.AppToolBar
import com.app.graphqltemplate.presentation.components.ErrorState
import com.app.graphqltemplate.presentation.components.Loader

@Composable
fun ContinentDetailScreen(
    continentDetailsVM: ContinentDetailsVM,
    navController: NavHostController
) {
    val uiState by continentDetailsVM.continentDetailsResponse.collectAsStateWithLifecycle()

    when (val currentState = uiState) {
        is ContinentDetailsScreenUiState.Error -> ErrorState(
            message = currentState.msg,
            modifier = Modifier.fillMaxSize()
        )

        ContinentDetailsScreenUiState.Initial -> Unit
        ContinentDetailsScreenUiState.Loading -> Loader(modifier = Modifier.fillMaxSize())
        is ContinentDetailsScreenUiState.Success -> ContinentDetailScreenData(currentState, navController)
    }
}

@Composable
fun ContinentDetailScreenData(
    currentState: ContinentDetailsScreenUiState.Success,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppToolBar(
            navController = navController,
            title = stringResource(R.string.continent_detail)
        )
        currentState.obj.continent?.let { data ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = data.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(12.dp)
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(data.countries) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),

                            ) {
                            Text(
                                text = it.emoji + " " + it.name,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(10.dp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "Language: " + it.languages[0].name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    bottom = 10.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}