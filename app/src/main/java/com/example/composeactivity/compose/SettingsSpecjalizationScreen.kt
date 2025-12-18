package com.example.composeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeactivity.data.entity.Specjalization
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.SettingsSpecjalizationViewModel
import kotlinx.coroutines.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSpecjalizationScreen(
    navController: NavController,
    onBack: () -> Unit,
    viewModel : SettingsSpecjalizationViewModel = hiltViewModel())  {

    val specjalizations by viewModel.specjalizations.collectAsState(initial = emptyList())
    val activeSpecjalizations by viewModel.activeSpecjalizations.collectAsState(initial = emptyList())
    var showDialog: Boolean by remember{ mutableStateOf(false)}

    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),
                title = { Text("Wizyty") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                ),
                navigationIcon = {
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
        ) {
            specjalizations.forEach {
                    spec ->
                SettingsSpecjalizationItem(
                    spec = spec,
                    isActive = activeSpecjalizations.any { it.id == spec.id },
                    onToggle = {spec, isChecked ->
                        if(isChecked) {
                            viewModel.addSpecjalization(spec)
                        }
                        else {
                            viewModel.deleteSpecjalization(spec)
                        }
                    }
                )

            }
            FloatingActionButton(
                onClick = {
                    showDialog = true
                },
            )
            {
                Icon(Icons.Default.Add, contentDescription = "Dodaj")
            }
        }


    }

    if(showDialog){
        AddSpecjalization(
            OnConfirm = {viewModel.name = it},
            viewModel.addSpecjalization(),
            onDismissRequest = {showDialog = false},
        )
    }
}


@Composable
fun SettingsSpecjalizationItem(
    spec: Specjalization,
    isActive: Boolean,
    onToggle: (spec: Specjalization,isChecked: Boolean) -> Unit,
) {
    val cardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFF5E6C8),
            Color(0xFFD2B48C)
        )
    )
    val switchGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF6E48AA), Color(0xFF9D50BB))
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardGradient)
            .clickable() {
            },
        color = Color.Transparent,


        ) {


        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = spec.name,
                modifier = Modifier.weight(2f)
                    .background(Color.Transparent),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                letterSpacing = 0.2.sp,
            )

            Switch(
                checked = isActive,
                onCheckedChange = { newValue ->
                    onToggle(spec, newValue)
                }
            )

        }
    }

}
@Composable
fun AddSpecjalization(
    OnConfirm: (String) -> Unit,
    onDismissRequest1: Job,
    onDismissRequest: () -> Unit
){
    var nameOfSpecjalization = remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),

        title = {
            Text(modifier = Modifier.background(MainColor),
                text = "Dodaj wizytę")},
        text  = {
            TextField(value = nameOfSpecjalization.value,
                onValueChange = {nameOfSpecjalization.value = it},
                label = {Text(" ")}
            )
        },

        confirmButton = { Button(
            onClick = { OnConfirm(nameOfSpecjalization.value) }) {
            Text("Akceptuj")
        } },


        )

}

