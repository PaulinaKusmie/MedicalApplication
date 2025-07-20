package com.example.composeactivity.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeactivity.compose.ui.theme.ComposeActivityTheme
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.SpecjalizationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(
    navController: NavController,
    onBack: () -> Unit)
    /*viewModel : SpecjalizationViewModel = viewModel())*/ {

    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),
                title = { Text("Konfiguracja") },
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
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .background(color = Color.Red)
        ) {
            Text("dpaaaa")
            DropdownMenuBoxTime()


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBoxTime()
{
    var expanded by remember{
        mutableStateOf(value = false)
    }
    var options = listOf<String>("piwrwsz", "drugi", "trzebi")
    var selectedOptions by remember{ mutableStateOf(options[0]) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded},
        modifier = Modifier.height(30.dp))
    {
        TextField(
            value = selectedOptions,
             onValueChange = {},
            readOnly = false,
            label = {Text("wybrana opcja ")}
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {}
        ){
            options.forEach { options ->
                DropdownMenuItem(
                    text = { Text(options) },
                    onClick = { TODO() },
                )
            }
        }
    }
}
