package com.example.composeactivity.compose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeactivity.compose.Tools.CounterWithDropdown
import com.example.composeactivity.compose.Tools.DropdownMenuBoxTime
import com.example.composeactivity.compose.Tools.TimeType
import com.example.composeactivity.compose.ui.theme.ComposeActivityTheme
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.ReminderViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.asFlow
import com.example.composeactivity.data.entity.Reminder
import kotlinx.coroutines.flow.forEach


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen( navController: NavController,
                         onBack: () -> Unit,
                    viewModels: ReminderViewModel = viewModel())
{ val reminders by viewModels.reminders.observeAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),
                title = { Text("Przypomnienia") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                ),  navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Powrót", tint = Color.Black)
                    }
                },
            )

        },
        containerColor = MainColor
    ) {
            padding ->
        Column(modifier = Modifier
            .padding(padding).padding(vertical = 20.dp, horizontal = 20.dp)) {

            val TimeTypeInt = 0;
            val mode = TimeType.DAY
            when(mode) {
                TimeType.HOUR -> 0
                TimeType.DAY -> 1
                TimeType.WEEK -> 2
                TimeType.MONTH -> 3
            }

            var chrum = Triple(0, 0, 0)


            reminders.forEach { x -> CounterWithDropdown(x.id, x.countReminder, x.TypeOfTime, onSelectionChange = {
                viewModels.updateReminder(it.first, it.second, it.third)
            }) }


            Button(modifier = Modifier.height(35.dp),onClick = {
                viewModels.addReminder()
            }) { Text("+") }





            //CounterWithDropdown(4,TimeTypeInt)
        }
    }

    MyScreen()
}



@Composable
fun MyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        FloatingActionButton(
            onClick = {  },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Dodaj")
        }
    }
}
