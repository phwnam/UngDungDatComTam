package com.xuong.ungdungdatcomtam.ui.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import com.xuong.ungdungdatcomtam.R
import com.xuong.ungdungdatcomtam.ui.controllerNav.Screen
import com.xuong.ungdungdatcomtam.ui.theme.Inter_Family

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repassword by remember { mutableStateOf("") }
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
                .weight(0.5f)
                .clip(RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Đăng ký",
                color = Color.White,
                fontFamily = Inter_Family,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.imgsplash),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
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
            Text(
                text = "Nhập lại mật khẩu",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Inter_Family,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(top = 12.dp)
            )
            val containerColor2 = Color(0xFFD9D9D9)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = repassword,
                onValueChange = { repassword = it },
                label = { Text("", fontSize = 16.sp) },
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = containerColor2,
                    unfocusedContainerColor = containerColor2,
                    disabledContainerColor = containerColor2,
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
                        navController.navigate(Screen.BOTTOM_MAIN_SCREEN.route)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFE724C)
                    ), shape = RoundedCornerShape(30.dp), modifier = Modifier
                ) {
                    Text(
                        text = "Đăng ký",
                        fontFamily = Inter_Family,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                TextButton(onClick = {
                    navController.navigate(Screen.LOGIN_SCREEN.route)
                }) {
                    Text("Đăng nhập", color = Color.White, fontFamily = Inter_Family)
                }
            }
        }

    }
}