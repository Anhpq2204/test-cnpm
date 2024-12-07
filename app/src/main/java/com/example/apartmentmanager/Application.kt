package com.example.apartmentmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme

//App bắt đầu tại đây
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApartmentManagerTheme {
                MainNavigation(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

//Hàm này quyết định xem trang đăng nhập hay giao diện chính của app sẽ được sử dụng
@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    //Biến này là một trạng thái (state) để cho app biết xuất hiện trang nào
    //role cho biết đối tượng nào đang đăng nhập (chủ nhà, quản lý, cư dân)
    var role by rememberSaveable { mutableIntStateOf(0) }
    var userID by rememberSaveable { mutableStateOf("") }
    Surface (
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = role == 0, //Nếu role = 0 thì hiển thị trang đăng nhập
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it })
        ) {
            LoginPage(onLoginClick = { r, id ->
                role = r
                userID = id
            })
        }
        AnimatedVisibility(
            visible = role == 1, //Nếu role = 1 thì hiển thị giao diện của cư dân
            enter = slideInHorizontally(initialOffsetX = { it }),
            exit = slideOutHorizontally(targetOffsetX = { it })) {
            TenantApp(onLogOut = { role = 0 })
        }
        AnimatedVisibility(
            visible = role == 2, //Nếu role = 2 thì hiển thị giao diện của quản lý
            enter = slideInHorizontally(initialOffsetX = { it }),
            exit = slideOutHorizontally(targetOffsetX = { it })) {
            ManagerApp(onLogOut = { role = 0 })
        }
    }
}

//Hàm này chỉ dùng để xem trước giao diện mà không cần chạy lại app
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    ApartmentManagerTheme {
        MainNavigation(modifier = Modifier.fillMaxWidth())
    }
}