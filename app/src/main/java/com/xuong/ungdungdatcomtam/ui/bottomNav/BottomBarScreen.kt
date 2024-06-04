package com.xuong.ungdungdatcomtam.ui.bottomNav

import com.xuong.ungdungdatcomtam.R

sealed class BottomBarScreen(
    val route: String, val title: String, val icon: Int
) {
    data object Home : BottomBarScreen(
        route = "Home", title = "Trang chủ", icon = R.drawable.imghome
    )

    data object Statistic : BottomBarScreen(
        route = "Statistic", title = "Thống kê", icon = R.drawable.imgcart
    )

    data object Manage : BottomBarScreen(
        route = "Manage", title = "Quản lý", icon = R.drawable.imgmanage
    )

    data object Profile : BottomBarScreen(
        route = "Profile", title = "Hỗ trợ", icon = R.drawable.imgprofile
    )
}