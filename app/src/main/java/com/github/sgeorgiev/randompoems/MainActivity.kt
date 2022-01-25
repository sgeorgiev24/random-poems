package com.github.sgeorgiev.randompoems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.sgeorgiev.randompoems.ui.Screen
import com.github.sgeorgiev.randompoems.ui.home.DataState
import com.github.sgeorgiev.randompoems.ui.home.HomeScreen
import com.github.sgeorgiev.randompoems.ui.home.HomeViewModel
import com.github.sgeorgiev.randompoems.ui.theme.RandomPoemsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomPoemsTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(
                        route = Screen.Home.route
                    ) {
                        val scaffoldState = rememberScaffoldState()
                        val viewModel: HomeViewModel = hiltViewModel()

                        HomeScreen(
                            state = viewModel.state.value,
                            scaffoldState = scaffoldState
                        )
                    }
                }
            }
        }
    }
}