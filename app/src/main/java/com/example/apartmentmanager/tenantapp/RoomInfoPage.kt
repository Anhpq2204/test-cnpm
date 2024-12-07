package com.example.apartmentmanager.tenantapp

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apartmentmanager.templates.InfoPage
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme

@Composable
fun RoomInfoPage(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    // Tạo state để lưu trữ kết quả trả về
    var roomDetails by remember { mutableStateOf<List<Any>>(emptyList()) }

    // Gọi hàm bất đồng bộ để lấy dữ liệu chi tiết phòng
   // LaunchedEffect(Unit) {
       roomDetails = RoomDetaiDisplay("T00001") // Giả sử "T00001" là tenantID
  //  }

    // Trang hiển thị thông tin phòng
    InfoPage(
        title = "Room Information",
        onBackClick = { onFunctionChange(0) },
        modifier = modifier
    ) {
        // Kiểm tra nếu roomDetails đã có dữ liệu
        if (roomDetails.isNotEmpty()) {
            Text(text = "Room Number: ${roomDetails[0]}")
          //  Text(text = "Floor:") // Thêm thông tin về tầng nếu có
            Text(text = "Area: ${roomDetails[2]}")
            Text(text = "Rent: ${roomDetails[1]}")
            Text(text = "Deposit:") // Thêm thông tin về tiền đặt cọc nếu có
            Text(text = "Status: ${roomDetails[3]}")
          //  Text(text = "Description:") // Thêm mô tả nếu có
            Text(text = "Electricity Unit Price: ${roomDetails[4]}") // Thêm ghi chú nếu có
            Text(text = "Water Unit Price:${roomDetails[5]}") // Thêm thông tin về thời gian cập nhật nếu có
        } else {
            Text(text = "Loading...") // Thông báo khi dữ liệu đang được tải
        }
    }
}

// Preview cho chế độ sáng
@Preview(showBackground = true)
@Composable
fun RoomInfoPagePreviewLightMode() {
    ApartmentManagerTheme {
        RoomInfoPage(modifier = Modifier, onFunctionChange = {})
    }
}

// Preview cho chế độ tối
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RoomInfoPagePreviewDarkMode() {
    ApartmentManagerTheme {
        RoomInfoPage(modifier = Modifier, onFunctionChange = {})
    }
}
