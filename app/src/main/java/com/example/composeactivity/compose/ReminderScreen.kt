package com.example.composeactivity.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeactivity.compose.Tools.CounterWithDropdown
import com.example.composeactivity.compose.Tools.DropdownMenuBoxTime
import com.example.composeactivity.compose.Tools.TimeType
import com.example.composeactivity.compose.ui.theme.ComposeActivityTheme
import com.example.composeactivity.ui.theme.MainColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen( navController: NavController,
                         onBack: () -> Unit)  {
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
            CounterWithDropdown(1, TimeType.DAY)
        }
    }
}

