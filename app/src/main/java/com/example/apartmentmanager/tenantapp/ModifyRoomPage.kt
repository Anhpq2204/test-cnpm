package com.example.apartmentmanager.tenantapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apartmentmanager.templates.InfoPage
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme

//Function 5: Thay đổi thông tin phòng
@Composable
fun ModifyRoomPage(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    InfoPage(
        title = "Modify Room",
        onBackClick = { onFunctionChange(0) }
    ) {
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ModifyRoomPagePreviewLightMode() {
    ApartmentManagerTheme {
        ModifyRoomPage(modifier = Modifier, onFunctionChange = {})
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ModifyRoomPagePreviewDarkMode() {
    ApartmentManagerTheme {
        ModifyRoomPage(modifier = Modifier, onFunctionChange = {})
    }
}