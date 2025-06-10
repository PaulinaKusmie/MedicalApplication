package com.example.composeactivity.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeactivity.R
import com.example.composeactivity.ui.theme.MainColor

@Composable
fun MainScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(50.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medical),
                        contentDescription = "Medical image",
                        modifier = Modifier.size(400.dp).padding(40.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                GenerateButton("Wizyty") {
                    navController.navigate("ExaminationView")
                }
                Spacer(modifier = Modifier.height(16.dp))
                GenerateButton("Badania medyczne") {
                    // Dodaj nawigację do kolejnego ekranu
                }
            }
        }
    }
}

@Composable
fun GenerateButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .height(80.dp)
            .width(310.dp),
        onClick = onClick
    ) {
        Text(label)
    }
}
