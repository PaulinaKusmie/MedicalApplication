package com.example.composeactivity.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.compose.Tools.DateType
import com.example.composeactivity.compose.Tools.EntryMode
import com.example.composeactivity.compose.Tools.WheelPickerDemo
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.Converter
import com.example.composeactivity.viewmodel.Mapper.VisitDateMapper.Companion.toEntity
import com.example.composeactivity.viewmodel.VisitDateViewModel
import java.time.LocalDateTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataAddEditScreen(
    entryMode: EntryMode,
    onBack: () -> Unit,
    viewModel: VisitDateViewModel = hiltViewModel(),
) {

    val dateVisitState = viewModel.DateVisitUI.value
    val userId = viewModel.userId


    if(userId > 0){
        viewModel.setMode(mode = entryMode)
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = dateVisitState.name ?: "Edytuj Dane",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = FontFamily.Monospace, // Możesz podmienić np. na FontFamily.Cursive lub własny font
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Powrót", tint = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MainColor),
                modifier = Modifier.shadow(4.dp)
            )
        },
        containerColor = MainColor
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
        ) {
            var showDialog by remember { mutableStateOf(false) }
            val pickedDate = remember { mutableStateOf<LocalDateTime?>(null) }
            val editingType = remember { mutableStateOf<DateType?>(null) }


            DateSection(
                label = "Data ostatniej wizyty:",
                date = dateVisitState.doneDate,
                onEditClick = {
                    pickedDate.value = Converter.longToLocalDateTime(dateVisitState.doneDate)
                    editingType.value = DateType.DONE
                    showDialog = true
                              },
                onClearClick = { viewModel.clearDate(DateType.DONE) },
            )



            DateSection(
                label = "Przewidywana data następnej wizyty:",
                date = dateVisitState.predictedDate,
                onEditClick = {
                    pickedDate.value = Converter.longToLocalDateTime(dateVisitState.predictedDate)
                    editingType.value = DateType.PREDICTED
                    showDialog = true
                              },
                onClearClick = {  viewModel.clearDate(DateType.PREDICTED)},

            )



            DateSection(label = "Umówiona data następnej wizyty:",
                date = dateVisitState.appointmentDate,
                onEditClick = {
                    pickedDate.value = Converter.longToLocalDateTime(dateVisitState.appointmentDate)
                    editingType.value = DateType.APPOITMENT
                    showDialog = true
                              },
                onClearClick = {  viewModel.clearDate(DateType.APPOITMENT) },

            )

            if (showDialog && editingType.value != null) {
                WheelPickerDemo(

                    OnDismissRequest = {
                        showDialog = false
                        when (editingType.value) {
                            DateType.DONE -> pickedDate.value?.let {
                                dateVisitState.doneDate = Converter.localDateTimeToLong(it)
                                viewModel.updateDoneDate(dateVisitState.toEntity())
                            }

                            DateType.PREDICTED -> pickedDate.value?.let {

                                dateVisitState.predictedDate = Converter.localDateTimeToLong(it)
                                viewModel.updatePredictedDate(dateVisitState.toEntity())
                            }

                            DateType.APPOITMENT -> pickedDate.value?.let {

                                dateVisitState.appointmentDate = Converter.localDateTimeToLong(it)
                                viewModel.updateAppointmentDate(dateVisitState.toEntity())
                            }

                            null -> {}
                        }
                        editingType.value = null
                    },
                    dateTime = pickedDate

                )
            }


        }
    }
}



@Composable
fun DateSection(
    label: String,
    date: Long?,
    onEditClick: (() -> Unit)? = null,
    onClearClick: (() -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = date?.let { Converter.longToFormattedDateTime(it) } ?: "Brak daty",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Row {
                    if (onEditClick != null) {
                        IconButton(onClick = onEditClick) {
                            Icon(Icons.Default.Edit, contentDescription = "Edytuj")
                        }
                    }
                    if (onClearClick != null) {
                        IconButton(onClick = onClearClick) {
                            Icon(Icons.Default.Delete, contentDescription = "Wyczyść", tint = Color.Red)
                        }
                    }
                }
            }
        }
    }
}


