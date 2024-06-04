package com.xuong.ungdungdatcomtam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.xuong.ungdungdatcomtam.R
import com.xuong.ungdungdatcomtam.ui.controllerNav.Screen
import com.xuong.ungdungdatcomtam.ui.theme.Inter_Family

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFoodScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF373232),
                    titleContentColor = Color("#ffffff".toColorInt()),
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(Color(0xFF373232), shape = RoundedCornerShape(15.dp)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(R.drawable.imgsplash),
                            modifier = Modifier
                                .size(56.dp)
                                .padding(4.dp),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Cum tứm đim",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(0.dp, 16.dp)
                                .fillMaxWidth(),
                            color = Color.White
                        )
                    }
                }
            )
        },
    ) {innerPadding->
        GetLayoutEditFood(innerPadding)

    }
}

@Composable
fun GetLayoutEditFood(innerPadding : PaddingValues = PaddingValues()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF373232)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.img_food),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Column(
            modifier = Modifier
                .background(
                    Color(0xFF373232),
                )
                .padding(16.dp),
        ) {


            val containerColor1 = Color(0xFFD9D9D9)
            Text(
                text = "Giá",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Inter_Family,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(top = 12.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
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
                text = "Tên món ăn",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = Inter_Family,
                fontWeight = FontWeight.W400,
                modifier = Modifier.padding(top = 12.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
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

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFB703)
                    ), shape = RoundedCornerShape(10.dp), modifier = Modifier
                ) {
                    Text(
                        text = "Lưu",
                        fontFamily = Inter_Family,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                }

            }

        }

    }
}