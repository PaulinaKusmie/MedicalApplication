package com.example.composeactivity.compose
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.vector.RootGroupName
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeactivity.compose.Tools.SimpleDialog
import com.example.composeactivity.compose.Tools.ToastEffect
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.SettingsExaminationViewModel
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsExaminationScreen(navController: NavController,
                              onBack: () -> Unit,
                              viewModel : SettingsExaminationViewModel = hiltViewModel()) {

    val examinations by viewModel.examinations.collectAsState(initial = emptyList())
    val activeExaminations by viewModel.activeExaminations.collectAsState(initial = emptyList())
    val errorMessage  by viewModel.errorMessage.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    val addedAlert by viewModel.addedAlert.collectAsState()


    addedAlert?.let {
        SimpleDialog(
            message = addedAlert.toString(),
            onDismiss = {viewModel.setAddedAlert(null)}
        )
    }

    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),
                title = { Text("Badania") },
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
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(count = examinations.size,
                key = {index -> examinations[index].id
                }) { index ->
                val exam = examinations[index]
                SettingsExaminationItem(
                exam = exam,
                    isActive = activeExaminations.any { it.id == exam.id },
                     onToggle = {exam, isChecked ->
                         if(isChecked) {
                             viewModel.addExaminationForUser(exam)
                         }
                         else {
                             viewModel.deleteExamination(exam)
                        }
                     }
                )

            }

        }


    }

    if(showDialog){
        AddExamination(
            errorMessage,
            OnConfirm = { viewModel.addExamination(it) },
            onDismiss = {
                viewModel.setShowDialog(false)
                viewModel.clearToastMessage()
            }
        )
    }
}




    @Composable
    fun SettingsExaminationItem(
        exam: Examination,
        isActive: Boolean,
        onToggle: (exam: Examination,isChecked: Boolean) -> Unit,
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
                .clickable() {// onClick(exam.id)
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
                    text = exam.name,
                    modifier = Modifier
                        .weight(2f)
                        .background(Color.Transparent),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    letterSpacing = 0.2.sp,
                )

                Switch(
                    checked = isActive,
                    onCheckedChange = { newValue ->
                        onToggle(exam, newValue)
                    }
                )

            }
        }

    }

    @Composable
    fun AddExamination(
        errorMessage: String?,
        OnConfirm: (String) -> Unit,
        onDismiss: () -> Unit,

    ){
        var nameOfExamination = remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = onDismiss,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),

            title = {
                Text(modifier = Modifier,
                    text = "Dodaj badanie")
                    },


                text  = {
                    Column(modifier = Modifier.padding(5.dp)) {
                        TextField(
                            value = nameOfExamination.value,
                            onValueChange = {nameOfExamination.value = it},
                            label = {Text(" ")},
                            isError = errorMessage != null
                        )

                        errorMessage?.let {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 4.dp),
                                color = Color.Red
                            )
                        }

                    }

            },

            confirmButton = {
                Button(onClick = { OnConfirm(nameOfExamination.value) },
            ) {
                Text("Akceptuj")
            } },

            dismissButton = {
                Button(
                    onClick = onDismiss ,
                ) {
                    Text("Anuluj")
                }
            }


        )

    }

