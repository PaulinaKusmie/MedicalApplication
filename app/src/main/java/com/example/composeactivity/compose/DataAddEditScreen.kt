package com.example.composeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.Converter
import com.example.composeactivity.viewmodel.VisitDateViewModel
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataAddEditScreen(
    entryMode: EntryMode,
    onSaved: () -> Unit,
    viewModel: VisitDateViewModel = viewModel(),

) {

    LaunchedEffect(entryMode) {
        viewModel.setMode(mode = entryMode)
    }

    val state = viewModel.dateVisitUI

    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),

                title = { Text("visitDate.name") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                )
            )
        },
        containerColor = MainColor

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                ,
            verticalArrangement = Arrangement.Center


        ) {
            var showDialog by remember { mutableStateOf(false) }
                Row(){
                    Text("Dzisiaj jest:")
                    val okon = state.value.doneDate
                    if(okon != null){ DateField(value = Converter.longToFormattedDateTime(okon)) }
                    Button(
                        onClick = { showDialog = true },
                    ) {Text("Edycja")}

                }
                 Spacer(modifier = Modifier.height(8.dp))

                Row(){
                    Text("Jutro jest:")
                    val okon = state.value.doneDate
                    if(okon != null){ DateField(value = Converter.longToFormattedDateTime(okon)) }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(){
                    Text("Wczoraj było:")
                    val okon = state.value.doneDate
                    if(okon != null){ DateField(value = Converter.longToFormattedDateTime(okon)) }
                    Spacer(modifier = Modifier.height(8.dp))
                }

            if(showDialog){
                WheelPickerDemo(OnDismissRequest = { showDialog = false })
            }


        }


    }
}

@Composable
fun DateField(value: String) {
    Text("$value")
}