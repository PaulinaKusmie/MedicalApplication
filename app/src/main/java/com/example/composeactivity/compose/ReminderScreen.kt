package com.example.composeactivity.compose
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FabPosition
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import com.example.composeactivity.data.entity.Reminder


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen( navController: NavController,
                         onBack: () -> Unit,
                    viewModel: ReminderViewModel = viewModel())
{

    val reminders by viewModel.reminders.observeAsState(initial = emptyList())
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastEvent.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

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
            .padding(padding)
            .padding(vertical = 20.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween) {

            LazyColumn {
                items(
                    count = reminders.size,
                    key = { index -> reminders[index].id }
                ) { index ->
                    val reminder = reminders[index]
                    CounterWithDropdown(
                        reminder = reminder,
                        onSelectionChange = { updatedReminder ->
                            viewModel.updateReminder(updatedReminder.id, updatedReminder.countReminder, updatedReminder.TypeOfTime)
                        },
                        OnSwipeStateChange = { toDelete ->
                            viewModel.deleteReminder(toDelete)
                        }
                    )
                }
            }


            FloatingActionButton(
                onClick = {  viewModel.addReminder() },

            ) {
                Icon(Icons.Default.Add, contentDescription = "Dodaj")

            }
        }
    }


}



@Composable
fun CounterWithDropdown (
    reminder : Reminder,
    onSelectionChange: (Reminder) -> Unit,
    OnSwipeStateChange: (Reminder) -> Unit)  {


    var value by remember(reminder) { mutableStateOf(reminder.countReminder) }
    var type by remember(reminder) { mutableStateOf(reminder.TypeOfTime) }


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
            enableDismissFromStartToEnd = false,
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
                        onClick = { val newValue = (value + 1).coerceAtLeast(1)
                            onSelectionChange(reminder.copy(countReminder = newValue, TypeOfTime = type))},

                        )

                    { Text("+") }
                    Button(
                        modifier = Modifier.height(35.dp),
                        onClick = { val newValue = (value - 1).coerceAtLeast(1)
                            onSelectionChange(reminder.copy(countReminder = newValue, TypeOfTime = type))}
                    )
                    { Text("-") }
                }

                DropdownMenuBoxTime(type, onSelectionChange = { newType ->
                    type = newType
                    onSelectionChange(reminder.copy(countReminder = value, TypeOfTime = type))
                })
            }
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

