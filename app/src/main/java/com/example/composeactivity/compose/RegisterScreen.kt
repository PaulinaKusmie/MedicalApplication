package com.example.composeactivity.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.LoginViewModel
import com.example.composeactivity.viewmodel.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController,
                   vm: RegisterViewModel = hiltViewModel()) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Rejstracja", fontWeight = FontWeight.Bold, fontSize = 26.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = vm.getEmail(),
                onValueChange = {
                    vm.updateEmail(it)
                    vm.clearMessage()
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = vm.getPassword(),
                onValueChange = {
                    vm.updatePassword(it)
                    vm.clearMessage()
                },
                label = { Text("Hasło") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = vm.getName(),
                onValueChange = {
                    vm.updateName(it)
                    vm.clearMessage()
                },
                label = { Text("Imię") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )


            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = vm.getAge(),
                onValueChange = {
                    vm.updateAge(it)
                    vm.clearMessage()
                },
                label = { Text("Wiek") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(Modifier.height(8.dp))

            Column(modifier = Modifier.fillMaxWidth().height(70.dp),
                verticalArrangement = Arrangement.Center) {

                Button(onClick = {vm.register()}, modifier = Modifier.weight(1f)) {
                    Text("Zarejestruj")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (vm.getMessage().isNotEmpty()) {
                Text(vm.getMessage(), color = Color.Red, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    if (vm.getShowDialog()) {
        AlertDialog(
            onDismissRequest = {vm.setShowDialog(false)},
            title = {
                Text(text = "Wpisz kod z maila")
            },

            text = {
                OutlinedTextField(
                    value = vm.getCode(),
                    onValueChange = {
                        vm.updateCode(it)
                        vm.clearMessage()
                    },
                    label = { Text("Kod") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )

                vm.getMessage()?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            },


            confirmButton = {
                Button(
                    onClick = { vm.confirmUser() })
                {
                    Text("OK")
                }
            }
        )
    }


//    LaunchedEffect(Unit) {
//        vm.NavigationEvent.collect { navController.navigate("MainScreen")}
//    }
}