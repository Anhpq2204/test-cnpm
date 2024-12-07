package com.example.apartmentmanager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme
import com.example.apartmentmanager.tenantapp.*

//Giống hàm MainNavigation, hàm này quyết định xem chức năng nào sẽ được hiển thị
@Composable
fun TenantApp(
    modifier: Modifier = Modifier,
    onLogOut: () -> Unit
) {
    //Chức năng mặc định = 0 (HomePage / Menu)
    var function by rememberSaveable { mutableIntStateOf(0) }

    //Các hiệu ứng chuyển động
    val enterFromRight = slideInHorizontally(initialOffsetX = { it })
    val enterFromLeft = slideInHorizontally(initialOffsetX = { -it })
    val exitToLeft = slideOutHorizontally(targetOffsetX = { -it })
    val exitToRight = slideOutHorizontally(targetOffsetX = { it })


    //Mỗi AnimatedVisibility định nghĩa một màn hình chức năng riêng, có bao gồm các hiệu ứng chuyển động
    AnimatedVisibility(
        visible = function == 0,
        enter = enterFromLeft,
        exit = exitToLeft
    ) {
        HomePage(modifier, onLogOut, onFunctionChange = { function = it })
    }
    //onFunctionChange để giúp điều hướng
    //ở HomePage, truyền vào onFunctionChange để gắn cho từng thẻ điều hướng chức năng
    //các chức năng khác mặc định sử dụng function = 0 gắn cho nút quay lại menu
    AnimatedVisibility(
        visible = function == 1,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        ApartmentInfoPage(modifier, onFunctionChange = { function = 0 })
    }
    AnimatedVisibility(
        visible = function == 2,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        RoomInfoPage(modifier, onFunctionChange = { function = 0 })
    }
    AnimatedVisibility(
        visible = function == 3,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        RentStatusPage(modifier, onFunctionChange = { function = 0 })
    }
    AnimatedVisibility(
        visible = function == 4,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        FinancialReportPage(modifier, onFunctionChange = { function = 0 })
    }
    AnimatedVisibility(
        visible = function == 5,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        ModifyRoomPage(modifier, onFunctionChange = { function = 0 })
    }
    AnimatedVisibility(
        visible = function == 6,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        ReportPage(modifier, onFunctionChange = { function = 0 })
    }
    AnimatedVisibility(
        visible = function == 7,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        SettingsPage(modifier, onFunctionChange = { function = 0 })
    }
    AnimatedVisibility(
        visible = function == 8,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        NotificationPage(modifier, onFunctionChange = { function = 0 })
    }
}


@Preview(showBackground = true)
@Composable
fun TenantAppPreviewLightMode() {
    ApartmentManagerTheme {
        TenantApp(modifier = Modifier, onLogOut = {})
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TenantAppPreviewDarkMode() {
    ApartmentManagerTheme {
        TenantApp(modifier = Modifier, onLogOut = {})
    }
}
