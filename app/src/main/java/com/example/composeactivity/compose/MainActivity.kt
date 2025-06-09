package com.example.composeactivity.compose

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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeactivity.R
import com.example.composeactivity.ui.theme.ComposeActivityTheme
import com.example.composeactivity.ui.theme.MainColor

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            ComposeActivityTheme {
                Scaffold(
                    modifier = Modifier.Companion
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .background(MainColor)
                    ) {
                        Column(
                            modifier = Modifier.Companion
                                .fillMaxSize()
                                .padding(innerPadding)
                                .padding(50.dp)
                        ) {
                            Box(
                                modifier = Modifier.Companion
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Companion.TopCenter
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.medical),
                                    contentDescription = "Medical image",
                                    modifier = Modifier.Companion.size(400.dp).padding(40.dp)
                                )

                            }
                            Spacer(modifier = Modifier.Companion.height(30.dp))
                            GenerateButton("Wizyty"){
                                ExaminationView().Screen()
                            }
                            Spacer(modifier = Modifier.Companion.height(16.dp))
                            GenerateButton("Badania medyczne"){

                            }

                        }
                    }

                }

            }

        }
    }

    @Composable
    fun GenerateButton(label: String, onClick: @Composable () -> Unit) {
        OutlinedButton(
            modifier = Modifier.Companion
                .height(80.dp)
                .width(310.dp),
            onClick = onClick as () -> Unit
        ) {
            Text("$label")
        }
    }


}