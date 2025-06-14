package com.example.composeactivity.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.viewmodel.ExaminationViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.CornerRadius
import com.example.composeactivity.compose.ui.theme.Purple40
import com.example.composeactivity.ui.theme.MainColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExaminationScreen(viewModel : ExaminationViewModel = viewModel()) {
    val examinations by viewModel.examinations.observeAsState(initial = emptyList())


    Scaffold(
        modifier = Modifier.background(MainColor),
        topBar = {
            TopAppBar(
                title = { Text("Lista wizyt") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)

        ) {
            examinations.forEach {
                exam -> ExaminationItem(
                    exam = exam,
                    onActiveChange = { isActive ->
                        viewModel.updateIsActive(exam.id, isActive)
                       // viewModel.deleteExamination(exam)
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
            .padding(vertical = 8.dp)
            //.border(2.dp, Purple40)
            .background( color = Purple40, shape = RoundedCornerShape(4.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = exam.name)
        Switch(
            checked = exam.isActive,
            onCheckedChange = onActiveChange
        )
    }
}
