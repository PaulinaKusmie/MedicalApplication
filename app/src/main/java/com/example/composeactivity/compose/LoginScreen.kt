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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.LoginViewModel
import androidx.hilt.navigation.compose.hiltViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController,
                onBack: () -> Unit,
                vm: LoginViewModel = hiltViewModel()) {

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
            Text("Logowanie", fontWeight = FontWeight.Bold, fontSize = 26.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = vm.email,
                onValueChange = {
                   vm.email = it
                    vm.clearMessage()
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = vm.password,
                onValueChange = {
                    vm.password = it
                    vm.clearMessage()
                },
                label = { Text("Hasło") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(Modifier.height(8.dp))

            Column(modifier = Modifier.fillMaxWidth().height(130.dp),
                verticalArrangement = Arrangement.Center) {

                Button(onClick = {vm.login()}, modifier = Modifier.weight(1f)) {
                    Text("Zaloguj")
                }
                Spacer(Modifier.width(8.dp))
                TextButton(onClick = {navController.navigate("RegisterScreen")}, modifier = Modifier.weight(1f)) {
                    Text("Zarejestruj")
                }
                Spacer(Modifier.width(8.dp))
                TextButton(onClick = {vm.forgotPassword()}, modifier = Modifier.weight(1f)) {
                    Text("Nie pamiętasz hasła?")

                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (vm.message.isNotEmpty()) {
                Text(vm.message, color = Color.Red, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    LaunchedEffect(Unit) {
        vm.NavigationEvent.collect { navController.navigate("MainScreen")} }
}


