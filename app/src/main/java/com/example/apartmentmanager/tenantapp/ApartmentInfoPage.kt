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
import com.google.firebase.firestore.FirebaseFirestore

//Function 1: Thông tin chung cư
@Composable
 fun ApartmentInfoPage(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    val apartmentRef = db.collection("apartmentInfo").document("general")

    // Tạo MutableState để lưu trữ dữ liệu và cập nhật khi có thay đổi
    var apartmentName by remember { mutableStateOf("Loading...") }
    var address by remember { mutableStateOf("Loading...") }
    var owner by remember { mutableStateOf("Loading...") }
    var contactInformation by remember { mutableStateOf("Loading...") }
    var nums_of_rooms by remember { mutableStateOf(0) }
    var apart_size by remember { mutableStateOf(0) }
    nums_of_rooms= countroomDisplay()
    // Lấy dữ liệu từ Firestore
    // Ở đây sử dụng LaunchedEffect để thực hiện tác vụ chỉ một lần khi Composable được khởi tạo
    LaunchedEffect(Unit) {
        apartmentRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("Firestore", "Document data: ${document.data}")
                    apartmentName = document.getString("name").orEmpty()
                    address = document.getString("address").orEmpty()
                    owner = document.getString("owner").orEmpty()
                    contactInformation = document.getString("contact").orEmpty()
                    apart_size= (document.getLong("area")?:0L).toInt()

                } else {
                    Log.d("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "get failed with ", exception)
            }
    }

    InfoPage(
        title = "Apartment Information",
        onBackClick = { onFunctionChange(0) },
        modifier = modifier
    ) {
        Text("Apartment Name: Chung cư $apartmentName")
        Text("Address: $address")
        Text("Number of Rooms:$nums_of_rooms ")
      //  Text("Number of Floors: ")
        Text("Area: $apart_size")
        Text("Description: ")
        Text("Owner: $owner")
        Text("Contact Information: $contactInformation")
    }
}

@Preview(showBackground = true)
@Composable
fun ApartmentInfoPagePreviewLightMode() {
    ApartmentManagerTheme {
        ApartmentInfoPage(modifier = Modifier, onFunctionChange = {})
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ApartmentInfoPagePreviewDarkMode() {
    ApartmentManagerTheme {
        ApartmentInfoPage(modifier = Modifier, onFunctionChange = {})
    }
}