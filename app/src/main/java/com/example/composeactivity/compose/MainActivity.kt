package com.example.composeactivity.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeactivity.ui.theme.ComposeActivityTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                val navController = rememberNavController()
                ComposeActivityTheme {
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable("MainScreen") {
                            MainScreen(navController)
                        }
                        composable("ExaminationScreen") {
                            ExaminationScreen()
                        }
                        composable("SpecjalizationScreen") {
                            SpecjalizationScreen(navController)
                        }
                        composable(route = "dataEntryAddWizyta/{specjalizationId}",
                            arguments = listOf(navArgument("specjalizationId") { type = NavType.IntType })
                        ) {
                            val id = it.arguments?.getInt("specjalizationId")!!
                            DataAddEditScreen(
                                entryMode = EntryMode.AddSpecjalizationVisit(id),
                                onSaved = { navController.popBackStack() })
                        }
                    }
                }
        }
    }





}