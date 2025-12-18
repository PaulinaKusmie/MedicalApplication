package com.example.composeactivity.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeactivity.R
import com.example.composeactivity.ui.theme.MainColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    HomeScreen()
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
                    navController.navigate("SpecjalizationScreen")
                }
                Spacer(modifier = Modifier.height(16.dp))
                GenerateButton("Badania medyczne") {
                    navController.navigate("ExaminationScreen")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(modifier = Modifier.height(80.dp).width(310.dp),
                    onClick = { navController.navigate("ConfigurationScreen") }){
                    Text("Ustawienia")
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

@Composable
fun HomeScreen() {
    BackHandler {

    }
}