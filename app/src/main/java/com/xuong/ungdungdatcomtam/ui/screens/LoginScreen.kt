package com.xuong.ungdungdatcomtam.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xuong.ungdungdatcomtam.R
import com.xuong.ungdungdatcomtam.room.UserDB
import com.xuong.ungdungdatcomtam.ui.controllerNav.Screen
import com.xuong.ungdungdatcomtam.ui.theme.Inter_Family
import com.xuong.ungdungdatcomtam.viewmodel.LoginViewModel
import com.xuong.ungdungdatcomtam.viewmodel.LoginViewModelFactory

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val dbUser = UserDB.getIntance(context)
    val userDao = dbUser.UserDAO()
    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(userDao)
    )
    loginViewModel.insertSampleAdmin()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isAuthenticated by loginViewModel.isAuthenticated.observeAsState()
    val role by loginViewModel.isRole.observeAsState()

    val isAuthenticatedState by remember { derivedStateOf { isAuthenticated }}

    LaunchedEffect(isAuthenticatedState) {
        if(isAuthenticatedState == true){
            Toast.makeText(context,"Đăng nhập thành công",Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.BOTTOM_MAIN_SCREEN.route)
        }else if(isAuthenticatedState == false){
            Toast.makeText(context,"Kiểm tra tài khoản mật khẩu",Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF373232)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color(0xFF282222),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .fillMaxWidth()
                .weight(0.9f)
                .clip(RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Đăng nhập",
                color = Color.White,
                fontFamily = Inter_Family,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.imgsplash),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
        }
        Column(
            modifier = Modifier
                .background(
                    Color(0xFF373232),
                )
                .weight(0.7f)
                .padding(16.dp),
        ) {
            Text(
                text = "Tên đăng nhập",
                color = Color.White,
                fontFamily = Inter_Family,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(top = 12.dp)
            )
            val containerColor = Color(0xFFD9D9D9)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                label = { Text("", fontSize = 16.sp) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                ),
                shape = RoundedCornerShape(10.dp),
            )
            Text(
                text = "Mật khẩu",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Inter_Family,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(top = 12.dp)
            )
            val containerColor1 = Color(0xFFD9D9D9)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text("", fontSize = 16.sp) },
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = containerColor1,
                    unfocusedContainerColor = containerColor1,
                    disabledContainerColor = containerColor1,
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                ),
                shape = RoundedCornerShape(10.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        when {
                            username.isEmpty() || password.isEmpty() -> {
                                Toast.makeText(
                                    context,
                                    "Tên đăng nhập và mật khẩu không được để trống",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            else -> {
                                loginViewModel.login(username, password)
                            }
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFE724C)
                    ), shape = RoundedCornerShape(30.dp), modifier = Modifier
                ) {
                    Text(
                        text = "Đăng nhập",
                        fontFamily = Inter_Family,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                TextButton(onClick = {
                    navController.navigate(Screen.SIGN_UP_SCREEN.route)
                }) {
                    Text("Đăng ký", color = Color.White, fontFamily = Inter_Family)
                }
            }

        }

    }
}