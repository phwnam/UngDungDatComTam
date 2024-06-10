package com.xuong.ungdungdatcomtam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xuong.ungdungdatcomtam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(

        content = { paddingValues: PaddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(color = Color.Black),
            ) {
                // Your content goes here, for example:
                Box(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxSize()
                        .background(color = Color(0xFF252121)),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp),


                        ) {
                        Row(
                            modifier = Modifier.padding(bottom = 50.dp, start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.imgzalopay),
                                contentDescription = "zalo",
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 8.dp)
                                    .fillMaxSize(0.5f)
                            )
                            Text(
                                text = "0888898680",
                                color = Color.White,
                                fontWeight = FontWeight.W700,
                                fontSize = 20.sp
                            )
                        }
                        Row(
                            modifier = Modifier.padding(bottom = 50.dp, start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image_email),
                                contentDescription = "email",
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 8.dp)
                                    .fillMaxSize(0.5f)
                            )
                            Text(
                                text = "quynhltph31900@fpt.edu.vn",
                                color = Color.White,
                                fontWeight = FontWeight.W700,
                                fontSize = 20.sp
                            )
                        }
                        Row(
                            modifier = Modifier.padding(start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image_phone),
                                contentDescription = "phone",
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 8.dp)
                                    .fillMaxSize(0.5f)
                            )
                            Text(
                                text = "0888898680",
                                color = Color.White,
                                fontWeight = FontWeight.W700,
                                fontSize = 20.sp
                            )
                        }

                    }
                }
            }
        }
    )
}