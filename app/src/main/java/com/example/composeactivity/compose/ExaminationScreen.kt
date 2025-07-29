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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeactivity.data.entity.Examination
import com.example.composeactivity.viewmodel.ExaminationViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeactivity.compose.Tools.GradientSwitch
import com.example.composeactivity.ui.theme.MainColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExaminationScreen(navController: NavController,
                      onBack: () -> Unit,
                      viewModel : ExaminationViewModel = viewModel())
{ val examinations by viewModel.examinations.observeAsState(initial = emptyList())


    Scaffold(
        topBar = {
            TopAppBar( modifier = Modifier.background(MainColor),
                title = { Text("Lista badań") },
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
            examinations.forEach {
                exam -> ExaminationItem(
                    exam = exam,
                    onActiveChange = { isActive ->
                        viewModel.updateIsActive(exam.id, isActive)
                    },
                    onClick = {
                        examid ->
                        navController.navigate("AddEditVisitExamination/$examid,${exam.name}")
                    }
                )

            }
        }
    }
}

@Composable
fun ExaminationItem(
    exam: Examination,
    onActiveChange: (Boolean) -> Unit,
    onClick: (Int) -> Unit
) {
    // Gradienty
    val cardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFF5E6C8), // Jasny beż
            Color(0xFFD2B48C)  // Klasyczny beż (tan)
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
            .clickable() { onClick(exam.id) },
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
                modifier = Modifier.weight(2f)
                    .background(Color.Transparent),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                letterSpacing = 0.2.sp,

            )
            GradientSwitch(
                checked = exam.isActive,
                onCheckedChange = onActiveChange,
                gradient = switchGradient
            )
        }
    }
}




