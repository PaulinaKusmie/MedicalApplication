package com.example.composeactivity.compose
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.ReminderViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composeactivity.data.entity.Reminder


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



            reminders.forEach { x -> CounterWithDropdown(x,
                onSelectionChange = {
                viewModels.updateReminder(it.id, it.countReminder, it.TypeOfTime)
            }, OnSwipeStateChange = {
                    viewModels.deleteReminder(it)
            })}

        }
    }

    FloatingActionButton(
        onClick = {  viewModels.addReminder() },
        modifier = Modifier
            .padding(24.dp)
    ) {
        Icon(Icons.Default.Add, contentDescription = "Dodaj")
    }
}



@Composable
fun CounterWithDropdown (
    reminder : Reminder,
    onSelectionChange: (Reminder) -> Unit,
    OnSwipeStateChange: (Reminder) -> Unit)  {


    var value by remember { mutableStateOf(reminder.countReminder) }
    var type by remember { mutableStateOf(reminder.TypeOfTime) }


    val state = rememberSwipeToDismissBoxState()
    if(state.currentValue == SwipeToDismissBoxValue.EndToStart)
        OnSwipeStateChange(reminder)


    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        SwipeToDismissBox(
            state = state,
            backgroundContent = { Modifier.background(Color.Gray)},
            enableDismissFromStartToEnd = false,  // przesuwanie w prawo
            enableDismissFromEndToStart = true   // przesuwanie w lewo
        ) {


            Row {
                TextField(
                    value = value.toString(),
                    onValueChange = { },
                    modifier = Modifier
                        .width(90.dp)
                        .padding(1.dp, 1.dp, 5.dp, 1.dp),
                    enabled = true,
                    readOnly = true
                )
                Column(modifier = Modifier) {
                    Button(
                        modifier = Modifier.height(35.dp),
                        onClick = { value += 1 }) { Text("+") }
                    Button(
                        modifier = Modifier.height(35.dp),
                        onClick = { value -= 1 }) { Text("-") }
                }

                DropdownMenuBoxTime(type, onSelectionChange = {
                    type = it
                })
            }

            reminder.countReminder = value
            reminder.TypeOfTime = type
            onSelectionChange(reminder)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBoxTime(typeOfTime : Int, onSelectionChange: (Int) -> Unit)
{
    var expanded by remember{ mutableStateOf(value = false) }
    var options = listOf("Godziny","Dni","Tygodnie","Miesiące" )
    var selectedIndex  by remember{ mutableStateOf(typeOfTime.coerceIn(options.indices))  }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded})
    {
        TextField(
            value = options[selectedIndex],
            onValueChange = { },
            readOnly = true,
            label = { Text("Jednostka czasu")},
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded  = false},

            ){
            options.forEachIndexed  { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedIndex = index
                        onSelectionChange(index)
                        expanded = false},
                )
            }
        }
    }

}

