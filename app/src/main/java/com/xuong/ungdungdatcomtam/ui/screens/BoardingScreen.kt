package com.xuong.ungdungdatcomtam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.xuong.ungdungdatcomtam.R
import com.xuong.ungdungdatcomtam.ui.controllerNav.Screen
import kotlinx.coroutines.delay

@Composable
fun BoardingScreen(navController: NavController){
    LaunchedEffect(Unit) {
        delay(3000L)
        navController.navigate(Screen.LOGIN_SCREEN.route){
            popUpTo(Screen.BOARDING_SCREEN.route) { inclusive = true }
        }
    }

    Column (
        modifier = Modifier
            .background(Color(0xFF282222))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.imgsplash),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}