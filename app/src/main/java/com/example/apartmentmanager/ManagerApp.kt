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
import com.example.apartmentmanager.managerapp.*
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme

@Composable
fun ManagerApp(
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


    AnimatedVisibility(
        visible = function == 0,
        enter = enterFromLeft,
        exit = exitToLeft
    ) {
        HomePage(modifier, onLogOut, onFunctionChange = { function = it })
    }
    AnimatedVisibility(
        visible = function == 1,
        enter = enterFromRight,
        exit = exitToRight
    ) {
        ApartmentInfoPage(modifier, onFunctionChange = { function = 0 })
    }
}

@Preview(showBackground = true)
@Composable
fun ManagerAppPreviewLightMode() {
    ApartmentManagerTheme {
        ManagerApp(modifier = Modifier, onLogOut = {})
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ManagerAppPreviewDarkMode() {
    ApartmentManagerTheme {
        ManagerApp(modifier = Modifier, onLogOut = {})
    }
}