package com.app.graphqltemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.graphqltemplate.presentation.continents.ContinentScreen
import com.app.graphqltemplate.presentation.continents.ContinentsVM
import com.app.graphqltemplate.ui.theme.GraphQLTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLTemplateTheme {
                val continentsVM: ContinentsVM = hiltViewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContinentScreen(
                        modifier = Modifier.padding(innerPadding),
                        continentsVM = continentsVM
                    )
                }
            }
        }
    }
}
