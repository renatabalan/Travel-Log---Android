package com.example.pmdassignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pmdassignment1.ui.*
import com.example.pmdassignment1.viewmodel.TravelViewModel
import com.example.pmdassignment1.ui.theme.PMDAssignment1Theme
import com.example.pmdassignment1.ui.theme.TravelBottomBar
import com.example.pmdassignment1.ui.theme.DestinationsScreen
import com.example.pmdassignment1.ui.theme.LogsScreen
import com.example.pmdassignment1.ui.theme.DetailsScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDAssignment1Theme {
                TravelApp()
            }
        }
    }
}

@Composable
fun TravelApp() {
    val navController = rememberNavController()
    val vm: TravelViewModel = viewModel()

    Scaffold(
        bottomBar = { TravelBottomBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "destinations",
            modifier = Modifier.padding(padding)
        ) {
            composable("destinations") {
                DestinationsScreen(
                    vm = vm,
                    onOpenDetails = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable("logs") {
                LogsScreen(vm = vm)
            }

            composable(
                route = "details/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: return@composable
                DetailsScreen(id = id, vm = vm, onBack = { navController.popBackStack() })
            }
        }
    }
}
