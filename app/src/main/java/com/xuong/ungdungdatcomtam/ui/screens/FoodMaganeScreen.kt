package com.xuong.ungdungdatcomtam.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.xuong.ungdungdatcomtam.R
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.model.entities.FoodEntity
import com.xuong.ungdungdatcomtam.ui.components.MyToolbar
import com.xuong.ungdungdatcomtam.ui.theme.Inter_Family
import com.xuong.ungdungdatcomtam.viewmodel.CategoryViewModel
import com.xuong.ungdungdatcomtam.viewmodel.FoodViewModel

private const val REQUEST_CODE_SELECT_IMAGE = 100

data class Foods(val image: Int, val name: String, val price: String)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodMaganeScreen(
    viewModel: FoodViewModel,
    categoryViewModel: CategoryViewModel,
    onBackClick: () -> Unit
) {


    val empty by remember { mutableStateOf("") }
    val foods by viewModel.foods.collectAsState(initial = emptyList())

    var foodName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("0") }
    var imagePath by remember { mutableStateOf("") }
    var categoriesId by remember { mutableStateOf("") }
    var inputImage by remember { mutableStateOf<Int?>(null) }
    val categories by categoryViewModel.categories.collectAsState(initial = emptyList())
    var selectedFood by remember { mutableStateOf<FoodEntity?>(null) }


    var showImage by remember { mutableStateOf(false) }
    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }

    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }



    Scaffold(
        topBar = {
            MyToolbar(title = "Danh sách món ăn ", onBackClick = onBackClick, onAddClick = {
                showAddDialog = true
            })
        },
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.Black),
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxSize()
                    .background(Color(0xFF282222))
            ) {
                if (foods.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .background(Color(0xFF282222))
                    ) {
                        androidx.compose.material3.Text(
                            text = "No name available",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 20.dp)
                    ) {
                        items(foods) { food ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                                    .padding(horizontal = 5.dp)
                                    .background(
                                        Color(0xFF2f2d2d),
                                        shape = RoundedCornerShape(15.dp)
                                    ),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                food.imagePath?.let {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = it),
                                        contentDescription = food.foodName,
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(20.dp))
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Column(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .weight(1f)
                                        .padding(10.dp),
                                    verticalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = food.foodName ?: "",
                                        fontSize = 16.sp,
                                        color = Color.White,

                                        )
                                    Text(
                                        text = "${food.price}",
                                        fontSize = 20.sp,
                                        color = Color(0xFFFE724C),
                                        fontWeight = FontWeight.Bold,
                                    )


                                }

                                Row(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .weight(0.3f)
                                        .padding(end = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.imgedit),
                                        contentDescription = null,
                                        modifier = Modifier.size(25.dp).clickable {
                                            selectedFood=food
                                            showEditDialog=true },

                                        )
                                    Image(
                                        painter = painterResource(id = R.drawable.imgdelete),
                                        contentDescription = null,
                                        modifier = Modifier.size(25.dp).clickable {
                                            selectedFood = food
                                            showDeleteDialog=true
                                        },
                                    )

                                }


                            }
                        }
                    }


                }

                if (showAddDialog) {
                    AddFoodDialog(
                        categories = categories,
                        onDismiss = { showAddDialog = false },
                        onConfirmAdd = { newFood ->
                            viewModel.addFood(newFood)
                            showAddDialog = false

                        }
                    )
                }

                if (showEditDialog && selectedFood!=null) {
                    EditFoodDialog(
                        categories = categories,
                        food = selectedFood!!,
                        onDismiss = { showEditDialog = false },
                        onConfirmEdit = { editFood ->
                            viewModel.updateFood(editFood)
                            showEditDialog = false

                        }
                    )
                }

                if (showDeleteDialog) {
                    DeleteLoaiSanphamDialog(onDismiss = { showDeleteDialog = false }, onConfirm = {
                        selectedFood?.let { food ->
                            viewModel.deleteFood(food)
                        }
                        showDeleteDialog = false
                    })
                }



            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodDialog(
    categories: List<CategoryEntity>,
    onDismiss: () -> Unit,
    onConfirmAdd: (FoodEntity) -> Unit
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var inputFoodName by remember { mutableStateOf("") }
    var inputcategory by remember { mutableStateOf(categories.firstOrNull()?.categoryName ?: "") }
    var inputFoodPrice by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    var selectedCategoryId by remember { mutableStateOf(categories.firstOrNull()?.cid ?: -1) }
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(500.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xFF373232)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = if (selectedImageUri != null) {
                        rememberAsyncImagePainter(model = selectedImageUri)
                    } else {
                        painterResource(id = R.drawable.img_add)
                    },
                    contentDescription = "Add Image",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            launcher.launch("image/*")
                        }
                )



                Column(
                    modifier = Modifier
                        .background(
                            Color(0xFF373232),
                        )
                        .padding(16.dp),
                ) {
                    Text(
                        text = " Loại Món  ",
                        color = Color.White,
                        fontFamily = Inter_Family,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    MySpinner(
                        items = categories.map { it.categoryName },
                        selectedItem = inputcategory,
                        onItemSelected = { inputcategory = it }
                    )
                    Text(
                        text = "Giá",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = Inter_Family,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(5.dp)),
                        value = inputFoodPrice,
                        onValueChange = {inputFoodPrice = it},
                        placeholder = { androidx.compose.material3.Text(text = "Nhập món ăn") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(5.dp)),
                        value = inputFoodName,
                        onValueChange = {inputFoodName = it},
                        placeholder = { androidx.compose.material3.Text(text = "Nhập món ăn") },
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier.width(110.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color("#E0AB3C".toColorInt()),
                                contentColor = Color.White
                            )
                        ) {
                            androidx.compose.material3.Text(text = "Hủy", fontSize = 14.sp)
                        }
                        Button(
                            onClick = {
                                onConfirmAdd(
                                    FoodEntity(
                                        id = 0,
                                        foodName = inputFoodName,
                                        imagePath = selectedImageUri?.toString(),
                                        price = inputFoodPrice.toIntOrNull()?:0,
                                        categoryId=inputcategory,

                                    )
                                )
                            },
                            modifier = Modifier.width(110.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color("#E0AB3C".toColorInt()),
                                contentColor = Color.White
                            )
                        ) {
                            androidx.compose.material3.Text(text = "Xác nhận", fontSize = 14.sp)
                        }
                    }

                }

            }
        }
    }


}


@Composable
fun EditFoodDialog(
    food: FoodEntity,
    categories: List<CategoryEntity>,
    onDismiss: () -> Unit,
    onConfirmEdit: (FoodEntity) -> Unit
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var inputFoodName by remember { mutableStateOf(food.foodName?:"") }
    var inputcategory by remember { mutableStateOf(food.categoryId?: categories.firstOrNull()?.categoryName ?: "") }
    var inputFoodPrice by remember { mutableStateOf( food.price.toString()?:"") }
    var addressImageUri = food.imagePath

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    var selectedCategoryId by remember { mutableStateOf(categories.firstOrNull()?.cid ?: -1) }
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(500.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xFF373232)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = if (selectedImageUri != null) {
                        rememberAsyncImagePainter(model = selectedImageUri)
                    } else {
                        rememberAsyncImagePainter(model = addressImageUri)
                    },
                    contentDescription = "Edit Image",
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            launcher.launch("image/*")
                        }
                )



                Column(
                    modifier = Modifier
                        .background(
                            Color(0xFF373232),
                        )
                        .padding(16.dp),
                ) {
                    Text(
                        text = " Loại Món  ",
                        color = Color.White,
                        fontFamily = Inter_Family,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    MySpinner(
                        items = categories.map { it.categoryName },
                        selectedItem = inputcategory,
                        onItemSelected = { inputcategory = it }
                    )
                    Text(
                        text = "Giá",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = Inter_Family,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(5.dp)),
                        value = inputFoodPrice,
                        onValueChange = {inputFoodPrice = it},
                        placeholder = { androidx.compose.material3.Text(text = "Nhập món ăn") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(5.dp)),
                        value = inputFoodName,
                        onValueChange = {inputFoodName = it},
                        placeholder = { androidx.compose.material3.Text(text = "Nhập món ăn") },
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier.width(110.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color("#E0AB3C".toColorInt()),
                                contentColor = Color.White
                            )
                        ) {
                            androidx.compose.material3.Text(text = "Hủy", fontSize = 14.sp)
                        }
                        Button(
                            onClick = {
                                onConfirmEdit(
                                    FoodEntity(
                                        id = food.id,
                                        foodName = inputFoodName,
                                        imagePath = selectedImageUri?.toString() ?: addressImageUri,
                                        price = inputFoodPrice.toIntOrNull()?:0,
                                        categoryId=inputcategory,

                                        )
                                )
                            },
                            modifier = Modifier.width(110.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color("#E0AB3C".toColorInt()),
                                contentColor = Color.White
                            )
                        ) {
                            androidx.compose.material3.Text(text = "Xác nhận", fontSize = 14.sp)
                        }
                    }

                }

            }
        }
    }


}



@Composable
fun MySpinner(
    items: List<String?>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clickable { expanded = !expanded }
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(selectedItem, modifier = Modifier.padding(start = 5.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            items.filterNotNull().forEach { item ->
                DropdownMenuItem(onClick = { onItemSelected(item)
                    expanded = false }) {
                    Text(
                        text = item,
                        color = if (item == selectedItem) MaterialTheme.colorScheme.primary else Color.Unspecified
                    )
                }
            }
        }
    }
}


@Composable
fun DeleteFoodDialog(
    food: FoodEntity,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    LocalContext.current
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color("#221F1F".toColorInt()))
                    .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                androidx.compose.material3.Text(
                    text = "Thông báo !!! ", color = Color.White, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                androidx.compose.material3.Text(
                    text = "Bạn có chắc chắn muốn xóa loại món ăn này?",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.width(110.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#E0AB3C".toColorInt()),
                            contentColor = Color.White
                        )
                    ) {
                        androidx.compose.material3.Text(text = "Cancel", fontSize = 14.sp)
                    }
                    Button(
                        onClick = {
                            onConfirm()
                            onDismiss()
                        },
                        modifier = Modifier.width(110.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#E0AB3C".toColorInt()),
                            contentColor = Color.White
                        )
                    ) {
                        androidx.compose.material3.Text(text = "Confirm", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

//@Composable
//fun GetLayoutList(innerPadding: PaddingValues = PaddingValues()) {
//    Box(
//        Modifier
//            .fillMaxSize()
//            .padding(
//                top = innerPadding.calculateTopPadding()
//            )
//            .background(Color(0xFF373232))
//    ) {
//        Column(
//            Modifier
//                .background(Color(0xFF373232))
//                .fillMaxSize()
//        ) {
//
//            val foods = listOf(
//                Foods(R.drawable.img_food, "Sườn bì", "28k"),
//                Foods(R.drawable.img_food, "B chả", "25k"),
//                Foods(R.drawable.img_food, "Trứng chả", "25k"),
//                Foods(R.drawable.img_food, "Sườn chả ", "28k"),
//                Foods(R.drawable.img_food, "Simple Desk", "$30.00"),
//                Foods(R.drawable.img_food, "Simple Desk", "$30.00"),
//            )
//
//            Spacer(modifier = Modifier.width(10.dp))
//
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//                    .padding(top = 20.dp)
//            ) {
//                items(items = foods) { item ->
//                    FoodRow(item = item)
//                }
//            }
//        }
//    }
//}

//@Composable
//fun FoodRow(item: Foods) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 5.dp)
//            .padding(horizontal = 5.dp)
//            .background(Color(0xFF2f2d2d), shape = RoundedCornerShape(15.dp)),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = item.image),
//            contentDescription = null,
//            modifier = Modifier
//                .width(80.dp)
//                .height(80.dp)
//                .clip(RoundedCornerShape(15.dp)),
//
//
//            )
//        Spacer(modifier = Modifier.width(8.dp))
//        Column(
//            modifier = Modifier
//                .height(100.dp)
//                .weight(1f)
//                .padding(10.dp),
//            verticalArrangement = Arrangement.SpaceBetween,
//        ) {
//            Text(
//                text = item.name,
//                fontSize = 16.sp,
//                color = Color.White,
//
//                )
//            Text(
//                text = item.price,
//                fontSize = 20.sp,
//                color = Color(0xFFFE724C),
//                fontWeight = FontWeight.Bold,
//            )
//
//
//        }
//
//        Row(
//            modifier = Modifier
//                .height(100.dp)
//                .weight(0.3f)
//                .padding(end = 10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.imgedit),
//                contentDescription = null,
//                modifier = Modifier.size(25.dp),
//
//                )
//            Image(
//                painter = painterResource(id = R.drawable.imgdelete),
//                contentDescription = null,
//                modifier = Modifier.size(25.dp),
//            )
//
//        }
//
//
//    }
//}


