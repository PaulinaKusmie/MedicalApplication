package com.example.composeactivity.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.viewmodel.ExaminationViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun ExaminationScreen(viewModel : ExaminationViewModel = viewModel()) {
    val examinations by viewModel.examinations.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista egzaminów") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            examinations.forEach { exam -> ExaminationItem(
                    exam = exam,
                    onActiveChange = { isActive ->
                        viewModel.updateIsActive(exam.id, isActive)
                    }
                )

            }
        }
    }
}

@Composable
fun ExaminationItem(
    exam: Examination,
    onActiveChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = exam.name)
        Switch(
            checked = exam.isActive,
            onCheckedChange = onActiveChange
        )
    }
}
