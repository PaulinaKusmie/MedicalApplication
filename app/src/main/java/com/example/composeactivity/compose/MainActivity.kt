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
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.composeactivity.compose.Tools.EntryMode
import com.example.composeactivity.ui.theme.ComposeActivityTheme
import com.example.composeactivity.utils.DailyNotificationWorker
import com.example.composeactivity.utils.NotificationUtils
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NotificationUtils.createNotificationChannel(this)



//        var myWorkRequest = PeriodicWorkRequestBuilder<DailyNotificationWorker>(Duration.ofMinutes(1)).build()
//
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//            "daily_notification",
//            ExistingPeriodicWorkPolicy.KEEP,
//            myWorkRequest
//        )


        setContent {
            val navController = rememberNavController()
            ComposeActivityTheme {
                NavHost(navController = navController, startDestination = "LoginScreen") {
                    composable("LoginScreen") {
                        LoginScreen(navController,
                            onBack = { navController.popBackStack() })
                    }
                    composable("RegisterScreen") {
                        RegisterScreen(navController)
                    }
                    composable("MainScreen") {
                        MainScreen(navController)
                    }
                    composable("ExaminationScreen") {
                        ExaminationScreen(
                            navController,
                            onBack = { navController.popBackStack() })
                    }
                    composable("SpecjalizationScreen") {
                        SpecjalizationScreen(
                            navController,
                            onBack = { navController.popBackStack() })
                    }
                    composable("ConfigurationScreen") {
                        ConfigurationScreen(
                            navController,
                            onBack = { navController.popBackStack() })
                    }
                    composable("ReminderScreen") {
                        ReminderScreen(
                            navController,
                            onBack = { navController.popBackStack() })
                    }
                    composable("SettingsSpecjalizationScreen") {
                        SettingsSpecjalizationScreen(
                            navController,
                            onBack = { navController.popBackStack() })
                    }
                    composable("SettingsExaminationScreen") {
                        SettingsExaminationScreen(
                            navController,
                            onBack = { navController.popBackStack() })
                    }
                    composable(
                        route = "AddEditVisitSpecjalization/{specjalizationId},{name}",
                        arguments = listOf(
                            navArgument("specjalizationId") { type = NavType.IntType },
                            navArgument("name") { type = NavType.StringType })
                    ) {
                        val id = it.arguments?.getInt("specjalizationId")!!
                        val name = it.arguments?.getString("name")!!
                        DataAddEditScreen(
                            entryMode = EntryMode.AddSpecjalizationVisit(id, name),
                            onBack = { navController.popBackStack() })
                    }

                    composable(
                        route = "AddEditVisitExamination/{examinationId},{name}",
                        arguments = listOf(
                            navArgument("examinationId") { type = NavType.IntType },
                            navArgument("name") { type = NavType.StringType })
                    ) {

                        val id = it.arguments?.getInt("examinationId")!!
                        val name = it.arguments?.getString("name")!!
                        DataAddEditScreen(
                            entryMode = EntryMode.AddExaminationVisit(id, name),
                            onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}


