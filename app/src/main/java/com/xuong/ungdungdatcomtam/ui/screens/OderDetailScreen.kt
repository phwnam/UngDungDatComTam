package com.xuong.ungdungdatcomtam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.xuong.ungdungdatcomtam.R
import com.xuong.ungdungdatcomtam.ui.theme.Inter_Family
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

data class Oder(val image: Int, val name: String, val price: String,val quantity:String)
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OderDetailScreen(){
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
        GetLayoutOder(innerPadding)

    }
}

@Composable
fun GetLayoutOder(innerPadding : PaddingValues = PaddingValues()) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding()
            )
            .background(Color(0xFF373232))) {
        Column(
            Modifier
                .background(Color(0xFF373232))
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2f2d2d)
                    ), shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f).padding(10.dp)
                ) {
                    Text(
                        text = "Xác nhận",
                        fontFamily = Inter_Family,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Button(
                    onClick = {

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2f2d2d)
                    ), shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f).padding(10.dp)
                ) {
                    Text(
                        text = "Hủy",
                        fontFamily = Inter_Family,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

            val oders = listOf(
                Oder(R.drawable.img_food, "Sườn bì", "28k","02"),
                Oder(R.drawable.img_food, "B chả", "25k","01"),
                Oder(R.drawable.img_food, "Trứng chả", "25k","02"),
            )

            Spacer(modifier = Modifier.width(10.dp))


            Text(text = "Món chính",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp),
                fontFamily = Inter_Family,
                fontWeight = FontWeight.ExtraBold
                )
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 10.dp)) {
                items(items = oders) { item ->
                    OderRow(item = item)
                }
            }
            Text(text = "Món thêm ",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp),
                fontFamily = Inter_Family,
                fontWeight = FontWeight.ExtraBold
            )
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 10.dp)) {
                items(items = oders) { item ->
                    OderRow(item = item)
                }
            }
            Text(text = "Topping",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp),
                fontFamily = Inter_Family,
                fontWeight = FontWeight.ExtraBold
            )
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 10.dp)) {
                items(items = oders) { item ->
                    OderRow(item = item)
                }
            }
        }
    }
}

@Composable
fun OderRow(item: Oder) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)
        .padding(horizontal = 5.dp)
        .background(Color(0xFF2f2d2d), shape = RoundedCornerShape(15.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(start = 10.dp)
                .clip(RoundedCornerShape(15.dp)),


            )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .height(100.dp)
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = item.name,
                fontSize = 16.sp,
                color = Color.White,

                )
            Text(text = item.price,
                fontSize = 20.sp,
                color = Color(0xFFFE724C),
                fontWeight = FontWeight.Bold,)
        }
        Text(text = item.quantity,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(end = 10.dp)
            )




    }
}