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
                            ExaminationScreen(
                                navController,
                                onBack = {navController.popBackStack() })
                        }
                        composable("SpecjalizationScreen") {
                            SpecjalizationScreen(
                                navController,
                                onBack = {navController.popBackStack() } )
                        }

                        composable(route = "AddEditVisitSpecjalization/{specjalizationId},{name}",
                            arguments = listOf(navArgument("specjalizationId") { type = NavType.IntType },
                                navArgument("name") { type = NavType.StringType } )
                        ) {
                            val id = it.arguments?.getInt("specjalizationId")!!
                            val name = it.arguments?.getString("name")!!
                            DataAddEditScreen(
                                entryMode = EntryMode.AddSpecjalizationVisit(id, name),
                                onBack = { navController.popBackStack() })
                        }

                        composable(route = "AddEditVisitExamination/{examinationId},{name}",
                            arguments = listOf(navArgument("examinationId") { type = NavType.IntType },
                                navArgument("name") { type = NavType.StringType } )
                        ) {
                            val id = it.arguments?.getInt("examinationId")!!
                            val name = it.arguments?.getString("name")!!
                            DataAddEditScreen(
                                entryMode = EntryMode.AddSpecjalizationVisit(id, name),
                                onBack = { navController.popBackStack() })
                        }
                    }
                }
        }
    }





}