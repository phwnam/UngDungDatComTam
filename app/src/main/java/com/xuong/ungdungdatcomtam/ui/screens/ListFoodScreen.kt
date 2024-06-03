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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

data class Foods(val image: Int, val name: String, val price: String)
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFoodScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF373232),
                    titleContentColor = Color("#ffffff".toColorInt()),
                ),
                title = {
                    Text(
                        text = "Cum tứm đim",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(0.dp, 16.dp)
                            .fillMaxWidth()
                    )
                }
            )
        },
    ) {innerPadding->
        GetLayoutList(innerPadding)

    }
}

@Composable
fun GetLayoutList(innerPadding : PaddingValues = PaddingValues()) {
    Box(Modifier.fillMaxSize().padding(
        top = innerPadding.calculateTopPadding()
    ).background(Color(0xFF373232))) {
        Column(
            Modifier
                .background(Color(0xFF373232))
                .fillMaxSize()
        ) {

            val foods = listOf(
                Foods(R.drawable.img_food, "Black Simple Lamp", "$12.00"),
                Foods(R.drawable.img_food, "Minimal Stand", "$25.00"),
                Foods(R.drawable.img_food, "Coffee Chair", "$20.00"),
                Foods(R.drawable.img_food, "Simple Desk", "$50.00"),
                Foods(R.drawable.img_food, "Simple Desk", "$30.00"),
                Foods(R.drawable.img_food, "Simple Desk", "$30.00"),
            )

            Spacer(modifier = Modifier.width(10.dp))

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 20.dp)) {
                items(items = foods) { item ->
                    FoodRow(item = item)
                }
            }
        }
    }
}

@Composable
fun FoodRow(item: Foods) {
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
                .height(80.dp),


        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .height(100.dp)
                .weight(1f)
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.name,
                fontSize = 16.sp,
                color = Color.White,

            )
            Text(text = item.price,
                fontSize = 20.sp,
                color = Color(0xFFFE724C),
                fontWeight = FontWeight.Bold,)

            Spacer(modifier = Modifier.weight(1f))


        }

        Row(
            modifier = Modifier.height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.imgdelete),
                contentDescription = null,
                modifier = Modifier.size(25.dp),

                )
            Image(
                painter = painterResource(id = R.drawable.imgedit),
                contentDescription = null,
                modifier = Modifier.size(25.dp),

                )
        }


    }
}


