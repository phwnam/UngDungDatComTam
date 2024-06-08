package com.xuong.ungdungdatcomtam.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import com.xuong.ungdungdatcomtam.R
import com.xuong.ungdungdatcomtam.model.entities.CategoryEntity
import com.xuong.ungdungdatcomtam.ui.components.MyToolbar
import com.xuong.ungdungdatcomtam.viewmodel.CategoryViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel, onBackClick: () -> Unit
) {
    var inputCategoryName by remember { mutableStateOf("") }
    val empty by remember { mutableStateOf("") }
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<CategoryEntity?>(null) }

    Scaffold(topBar = {
        MyToolbar(title = "Danh sách loại", onBackClick = onBackClick, onAddClick = {
            showAddDialog = true
        })
    }, content = { paddingValues: PaddingValues ->
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
                if (categories.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .background(Color(0xFF282222))
                    ) {
                        Text(text = "No name available", color = Color.White, fontSize = 20.sp)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF282222)),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(categories) { category ->
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable { }
                                .padding(10.dp)
                                .height(82.dp)
                                .background(
                                    Color("#2B2929".toColorInt()), shape = RoundedCornerShape(10.dp)
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("${category.cid}", color = Color.White, fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(20.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        "${category.categoryName}",
                                        color = Color.White,
                                        fontSize = 20.sp
                                    )
                                }
                                Image(painter = painterResource(id = R.drawable.ic_edit),
                                    contentDescription = "ImageEdit",
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clickable {
                                            selectedCategory = category
                                            showEditDialog = true
                                        })
                                Spacer(modifier = Modifier.width(25.dp))
                                Image(painter = painterResource(id = R.drawable.ic_delete),
                                    contentDescription = "ImageDelete",
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clickable {
                                            selectedCategory = category
                                            showDeleteDialog = true
                                        })
                                Spacer(modifier = Modifier.width(25.dp))
                            }
                        }
                    }
                }

                if (showAddDialog) {
                    AddCategoryDialog(onDismiss = { showAddDialog = false },
                        onConfirm = {
                            viewModel.addCategory(
                                CategoryEntity(
                                    0,
                                    inputCategoryName,
                                )
                            )
                            showAddDialog = false
                            inputCategoryName = empty
                        },
                        inputCategoryName = inputCategoryName,
                        onInputChange = { inputCategoryName = it })
                }

                if (showEditDialog) {
                    EditCategoryDialog(onDismiss = { showEditDialog = false },
                        onConfirm = {
                            selectedCategory?.let {
                                viewModel.updateCategory(
                                    it.copy(
                                        categoryName = inputCategoryName
                                    )
                                )
                            }
                            showEditDialog = false
                            inputCategoryName = empty
                        },
                        inputCategoryName = inputCategoryName,
                        onInputChange = { inputCategoryName = it })
                }
                if (showDeleteDialog) {
                    DeleteLoaiSanphamDialog(onDismiss = { showDeleteDialog = false }, onConfirm = {
                        selectedCategory?.let { category ->
                            viewModel.deleteCategory(category)
                        }
                        showDeleteDialog = false
                    })
                }
            }
        }
    })
}

@Composable
fun AddCategoryDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    inputCategoryName: String,
    onInputChange: (String) -> Unit
) {
    val context = LocalContext.current
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
                Text(text = "Category", color = Color.White, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(5.dp)),
                    value = inputCategoryName,
                    onValueChange = onInputChange,
                    placeholder = { Text(text = "Nhập Category") },
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
                        Text(text = "Hủy", fontSize = 14.sp)
                    }
                    Button(
                        onClick = {
                            if (inputCategoryName.isEmpty()) {
                                Toast.makeText(
                                    context, "Vui lòng nhập Category", Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                onConfirm(inputCategoryName)
                                onDismiss()
                            }
                        },
                        modifier = Modifier.width(110.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#E0AB3C".toColorInt()),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Xác nhận", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun EditCategoryDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    inputCategoryName: String,
    onInputChange: (String) -> Unit
) {
    val context = LocalContext.current
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
                Text(text = "Category", color = Color.White, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(5.dp)),
                    value = inputCategoryName,
                    onValueChange = onInputChange,

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
                        Text(text = "Hủy", fontSize = 14.sp)
                    }
                    Button(
                        onClick = {
                            if (inputCategoryName.isEmpty()) {
                                Toast.makeText(
                                    context, "Vui lòng nhập Category", Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                onConfirm(inputCategoryName)
                                onDismiss()
                            }
                        },
                        modifier = Modifier.width(110.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#E0AB3C".toColorInt()),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Xác nhận", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteLoaiSanphamDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit
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
                Text(
                    text = "Thông báo !!! ", color = Color.White, modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
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
                        Text(text = "Cancel", fontSize = 14.sp)
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
                        Text(text = "Confirm", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}
