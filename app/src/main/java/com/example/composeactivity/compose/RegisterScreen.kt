package com.example.composeactivity.compose

import android.R
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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

    val showDialog by vm.showDialog.collectAsState()
    val message by vm.message.collectAsState()
    val messageCode by vm.messageCode.collectAsState()
    val isLoading by vm.isLoading.collectAsState()

    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()
    val name by vm.name.collectAsState()
    val age by vm.age.collectAsState()

    val code by vm.code.collectAsState()

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
                value = email,
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
                value = password,
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
                value = name,
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
                value = age,
                onValueChange = {
                    vm.updateAge(it)
                    vm.clearMessage()
                },
                label = { Text("Wiek") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(Modifier.height(8.dp))

                Button(
                    onClick = { vm.register() },
                    modifier = Modifier.fillMaxWidth()) {
                    if(isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    }
                    Text(if(isLoading) "Rejstrowanie..." else "Zarejestruj")
                }

            Spacer(modifier = Modifier.height(12.dp))

            message?. let{
                Text(it,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            }

        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {vm.setShowDialog(false)},
            title = {
                Text(text = "Wpisz kod z maila")
            },

            text = {
                Column(){
                    OutlinedTextField(
                        value = code ?: " ",
                        onValueChange = {
                            vm.updateCode(it)
                            vm.clearMessage()
                        },
                        label = { Text("Kod") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = messageCode!= null
                    )

                    messageCode?.let {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
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


    LaunchedEffect(Unit) {
        vm.NavigationEvent.collect { navController.navigate("MainScreen")}
    }
}





