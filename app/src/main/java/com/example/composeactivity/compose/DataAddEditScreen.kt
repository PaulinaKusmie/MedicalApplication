package com.example.composeactivity.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.ui.theme.MainColor
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

    Scaffold(
        topBar = {
            //TopAppBar( modifier = Modifier.background(MainColor),
                //title = { Text(visitDate.name) },
                //colors = TopAppBarDefaults.topAppBarColors(
               //     containerColor = MainColor
               // )
          //  )
        },
        containerColor = MainColor

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)) {
            val currentDate = LocalDateTime.now()
            Text("Dzisiaj jest: $currentDate")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Dzisiaj jest: $currentDate")
            Spacer(modifier = Modifier.height(9.dp))
            Text("Dzisiaj jest: $currentDate")


        }
    }
}