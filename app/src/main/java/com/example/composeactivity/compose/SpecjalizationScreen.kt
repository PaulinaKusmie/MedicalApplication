package com.example.composeactivity.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.viewmodel.ExaminationViewModel
import com.example.composeactivity.viewmodel.SpecjalizationViewModel

@Composable
fun SpecjalizationScreen(viewModel : SpecjalizationViewModel = viewModel()) {
    val specjalizations by viewModel.specjalizations.observeAsState(initial = emptyList())

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("To jest ekran Specjalization medycznych!")
    }
}
