package com.example.composeactivity.compose

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.WorkManager
import com.example.composeactivity.ui.theme.ComposeActivityTheme
import com.example.composeactivity.utils.DailyNotificationWorker
import com.example.composeactivity.utils.NotificationUtils


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NotificationUtils.createNotificationChannel(this)

        val myWorkRequest = DailyNotificationWorker(this, null)
        WorkManager.getInstance(this).enqueue(myWorkRequest)

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, 1)
        NotificationUtils.scheduleNotification(this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE)
        )



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
                            onBack = { navController.popBackStack() })
                    }
                    composable("SpecjalizationScreen") {
                        SpecjalizationScreen(
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
                            entryMode = EntryMode.AddSpecjalizationVisit(id, name),
                            onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}


