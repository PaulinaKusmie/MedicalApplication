package com.example.composeactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.composeactivity.ui.theme.ComposeActivityTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeactivity.ui.theme.MainColor


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            ComposeActivityTheme {
                Scaffold(modifier = Modifier
           ) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(MainColor)){
                        Column (modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(50.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.TopCenter) {
                                Image(painter = painterResource(id = R.drawable.medical),
                                    contentDescription = "Medical image",
                                    modifier = Modifier.size(400.dp).padding(40.dp))

                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            OutlinedTextFieldSample(label = "Email")
                            Spacer(modifier = Modifier.height(16.dp))
                            OutlinedTextFieldSample(label = "Hasło")
                            Spacer(modifier = Modifier.height(16.dp))
                            OutlinedButton(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(140.dp)
                                    .align(Alignment.End)
                                    .padding(0.dp,0.dp,35.dp,0.dp,)
                                ,onClick = {  }) {
                                Text("Zaloguj")
                            }
                        }
                    }

                }

            }

        }
    }



    @Composable
    fun OutlinedTextFieldSample(label: String) {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("$label") }
        )
    }

}



