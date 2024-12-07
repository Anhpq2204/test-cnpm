package com.example.apartmentmanager.tenantapp

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

//Function 4: Báo cáo tài chính
@Composable
fun FinancialReportPage(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    // Tạo state để lưu trữ kết quả trả về
    var roomDetails by remember { mutableStateOf<List<Any>>(emptyList()) }
    var roomReport by remember { mutableStateOf<List<Any>>(emptyList()) }

    // Gọi hàm bất đồng bộ để lấy dữ liệu chi tiết phòng
    // LaunchedEffect(Unit) {
    roomDetails = RoomDetaiDisplay("T00001") // Giả sử "T00001" là tenantID
    roomReport = FinacialReportDisplay("T00001",10,2024)
    //  }
    var electricBill=roomDetails[4]*roomReport[1]
    var waterBill=roomDetails[5]*roomReport[2]
    var totalBill=electricBill+waterBill+roomReport[3]+roomReport[4]

    // Trang hiển thị thông tin phòng
    InfoPage(
        title = "Room Informat",
        onBackClick = { onFunctionChange(0) },
        modifier = modifier
    ) {
        // Kiểm tra nếu roomDetails đã có dữ liệu
        if (roomDetails.isNotEmpty()) {
            Text(text = "Room Number: ${roomReport[0]}")
            Text(text = "Electricity Comsumption: ${roomReport[1]}")
            Text(text = "Electricity Unit Price: ${roomDetails[4]}") // Thêm ghi chú nếu có
          //  Text(text = "Electricity Bill: $electricBill") // Thêm thông tin về tiền đặt cọc nếu có
            Text(text = "Water Comsumption: ${roomReport[2]}")
            Text(text = "Water Unit Price:${roomDetails[5]}") // Thêm thông tin về thời gian cập nhật nếu có
          //  Text(text = "Water Bill: $waterBill")
            Text(text = "Internet: ${roomReport[3]}")
            //  Text(text = "Description:") // Thêm mô tả nếu có
            Text(text = "Service: ${roomReport[4]}") // Thêm ghi chú nếu có
          //  Text(text = "Total Bill: $totalBill") // Thêm thông tin về thời gian cập nhật nếu có
        } else {
            Text(text = "Loading...") // Thông báo khi dữ liệu đang được tải
        }
    }
}

private operator fun Any.plus(waterBill: Any): Any {
    return when {
        this is Double && waterBill is Double -> this + waterBill
        this is Int && waterBill is Int -> this + waterBill
        this is Float && waterBill is Float -> this + waterBill
        else -> throw IllegalArgumentException("Both operands must be of type Double, Int, or Float")
    }
}


private operator fun Any.times(any: Any): Any {
    // Kiểm tra nếu cả hai đối số đều là kiểu Double
    return when {
        this is Double && any is Double -> this * any
        this is Int && any is Int -> this * any
        this is Float && any is Float -> this * any
        else -> throw IllegalArgumentException("Both operands must be of type Double, Int, or Float")
    }
}


@Preview(showBackground = true)
@Composable
fun FinancialReportPagePreviewLightMode() {
    ApartmentManagerTheme {
        FinancialReportPage(modifier = Modifier, onFunctionChange = {})
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FinancialReportPagePreviewDarkMode() {
    ApartmentManagerTheme {
        FinancialReportPage(modifier = Modifier, onFunctionChange = {})
    }
}