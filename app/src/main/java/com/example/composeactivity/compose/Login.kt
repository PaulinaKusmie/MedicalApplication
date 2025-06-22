package com.example.composeactivity.compose

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeactivity.R
import com.example.composeactivity.ui.theme.MainColor

object Login {

    @Composable
    fun Screen() {
        Box(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(MainColor)
        ) {
            Column(
                modifier = Modifier.Companion
                    .fillMaxSize()
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
                        modifier = Modifier.Companion
                            .size(400.dp)
                            .padding(40.dp)
                    )
                }
                Spacer(modifier = Modifier.Companion.height(30.dp))
                //OutlinedTextFieldSample(label = "Email")
                Spacer(modifier = Modifier.Companion.height(16.dp))
               // OutlinedTextFieldSample(label = "Hasło")
                Spacer(modifier = Modifier.Companion.height(16.dp))
                OutlinedButton(
                    modifier = Modifier.Companion
                        .height(40.dp)
                        .width(140.dp)
                        .align(Alignment.Companion.End)
                        .padding(end = 35.dp),
                    onClick = { /* Obsługa logowania */ }
                ) {
                    Text("Zaloguj")
                }
            }
        }
    }

//    @Composable
//    fun OutlinedTextFieldSample(label: String) {
//        var text by remember { mutableStateOf("") }
//        OutlinedTextField(
//            value = text,
//            onValueChange = { text = it },
//            label = { Text(label) }
//        )
//    }
}