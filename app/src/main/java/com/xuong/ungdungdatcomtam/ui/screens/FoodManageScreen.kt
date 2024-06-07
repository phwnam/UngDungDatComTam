package com.xuong.ungdungdatcomtam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.xuong.ungdungdatcomtam.R
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodManageScreen(){
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
        GetLayout(innerPadding)

    }
}

@Composable
fun GetLayout(innerPadding : PaddingValues = PaddingValues()) {
    Column {
        Column(modifier = Modifier
            .fillMaxSize()
            .height(630.dp)
            .background(Color(0xFF373232))
            .padding(
                top = innerPadding.calculateTopPadding()
            )) {
                ItemRow("Quản lý loại món ăn", R.drawable.imgsplash)
            ItemRow("Quản lý món ăn", R.drawable.imgsplash)
        }
    }
}

@Composable
fun ItemRow(name: String, logo: Int){
    Row(
        modifier = Modifier
            .padding(5.dp)
            .background(Color(0xFF373232), shape = RoundedCornerShape(15.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = logo),
            modifier = Modifier
                .size(56.dp)
                .padding(4.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = name,color = Color.White, modifier = Modifier.weight(1f))
    }
}


