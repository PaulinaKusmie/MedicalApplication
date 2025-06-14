package com.example.composeactivity.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.data.entity.VisitDate
import com.example.composeactivity.viewmodel.VisitDateViewModel


@Composable
fun VisitDateScreen(viewModel : VisitDateViewModel = viewModel()) {

    val nowaWizyta = VisitDate(
        0,
        procedureId = 123,
        date = System.currentTimeMillis(),
        doneDate = false,
        predictedDate = true,
        appointmentDate = false
    )



    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Rakord został dodany")
    }
}
