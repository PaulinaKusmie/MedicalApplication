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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeactivity.ui.theme.MainColor
import com.example.composeactivity.viewmodel.LoginViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController,
                vm: LoginViewModel = hiltViewModel()) {

    val uiState by vm.uiState.collectAsState()
    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
            .padding(16.dp)
            .imePadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Logowanie",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    vm.setEmail(it)
                    vm.clearMessage()
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !uiState.isLoading,
//                KeyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Email,
//                    imeAction = ImeAction.Next
//                ),
                keyboardActions = KeyboardActions(
                    onNext  = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                isError = uiState.message?.contains("login", ignoreCase = true) == true
            )

            Spacer(Modifier.height(12.dp))

            // Password field
            OutlinedTextField(
                value = password,
                onValueChange = {
                    vm.setPassword(it)
                    vm.clearMessage()
                },
                label = { Text("Hasło") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                enabled = !uiState.isLoading,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions (
                    onDone = { keyboardController?.hide()
                    vm.login()}
                ),
                isError = uiState.message?.contains("hasło", ignoreCase = true) == true
            )

            Spacer(Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth().height(130.dp),
                verticalArrangement = Arrangement.Center) {

                Button(
                    onClick = { keyboardController?.hide()
                        vm.login() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isLoading)
                {
                    if(uiState.isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth =  2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(if(uiState.isLoading) "Logowanie..." else "Zaloguj" )
                }

                Spacer(Modifier.width(8.dp))

                TextButton(
                    onClick = {navController.navigate("RegisterScreen")},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isLoading) {
                    Text("Nie masz konta? Zarejestruj się")
                }

                Spacer(Modifier.width(8.dp))

                TextButton(
                    onClick = {vm.forgotPassword()},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isLoading) {
                    Text("Nie pamiętasz hasła?")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            uiState.message?.let {
                Text(it,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth())
            }

        }
    }

    LaunchedEffect(Unit) {
        vm.NavigationEvent.collect { navController.navigate("MainScreen")} }
}


