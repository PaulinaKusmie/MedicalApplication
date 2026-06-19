package com.example.composeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.composeactivity.compose.Tools.SimpleDialog
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
    val errorMessage by viewModel.errorMessage.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    val addedAlert by viewModel.addedAlert.collectAsState()

    addedAlert?.let{
        SimpleDialog(
            message = addedAlert.toString(),
            onDismiss = {viewModel.setAddedAlert(null)})
    }

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
        containerColor = MainColor,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.setShowDialog(true)
                },
            )
            {
                Icon(Icons.Default.Add, contentDescription = "Dodaj")
            }
        }

    ) { padding ->
        LazyColumn (
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(count = specjalizations.size,
                key = {index -> specjalizations[index].id
                }) { index ->
                val spec = specjalizations[index]
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

        }


    }

    if(showDialog){
        AddSpecjalization(
            errorMessage = errorMessage,
            OnConfirm = { viewModel.addSpecjalization(it) },
            onDismiss = { viewModel.setShowDialog(false)
            viewModel.setErrorMessage(null)}
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
    errorMessage: String?,
    OnConfirm: (String) -> Unit,
    onDismiss:() -> Unit
){
    var nameOfSpecjalization = remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),

        title = {
            Text(modifier = Modifier.padding(5.dp),
                text = "Dodaj wizytę")
                },

        text  = {
            Column (modifier = Modifier.padding(5.dp)){
                TextField(
                    value = nameOfSpecjalization.value,
                    onValueChange = {nameOfSpecjalization.value = it},
                    label = {Text(" ")},
                    isError = errorMessage != null
                )
                errorMessage?.let{
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Red)
                }

            }

        },

        confirmButton = {
            Button(onClick = { OnConfirm(nameOfSpecjalization.value) })
            {
            Text("Akceptuj")
            }
        },

        dismissButton = {
            Button(onClick = onDismiss)
            {
                Text("Anuluj")
            }
        }


    )

}

