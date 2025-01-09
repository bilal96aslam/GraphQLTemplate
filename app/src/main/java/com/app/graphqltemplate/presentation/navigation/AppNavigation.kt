package com.app.graphqltemplate.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.graphqltemplate.presentation.continentDetails.ContinentDetailScreen
import com.app.graphqltemplate.presentation.continentDetails.ContinentDetailsVM
import com.app.graphqltemplate.presentation.continents.ContinentScreen
import com.app.graphqltemplate.presentation.continents.ContinentsVM
import com.app.graphqltemplate.utils.Constant

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = TopLevelDestination.Continents.route
    ) {
        composable(
            route = TopLevelDestination.Continents.route
        ) {
            val continentsVM: ContinentsVM = hiltViewModel()
            ContinentScreen(continentsVM = continentsVM, navController = navController) { code ->
                navController.navigate(TopLevelDestination.ContinentDetails.withArgs(code))
            }
        }

        composable(
            route = TopLevelDestination.ContinentDetails.route + "/{${Constant.CODE}}",
            arguments = listOf(
                navArgument(Constant.CODE) {
                    type = NavType.StringType
                }
            )
        ) {
            val continentDetailsVM: ContinentDetailsVM = hiltViewModel()
            val code = it.arguments?.getString(Constant.CODE) ?: return@composable
            LaunchedEffect(key1 = code) {
                continentDetailsVM.getContinentDetails(code)
            }
            ContinentDetailScreen(
                continentDetailsVM = continentDetailsVM,
                navController = navController
            )
        }
    }
}