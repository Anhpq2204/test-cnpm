package com.example.apartmentmanager.tenantapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

val db = FirebaseFirestore.getInstance()

// Suspend function để lấy thông tin phòng
suspend fun getRoomInfo(tenantID: String): String {
    val roomIDref = db.collection("tenants").document(tenantID)

    return try {
        val document = roomIDref.get().await()
        if (document.exists()) {
            Log.d("Firestore", "Document data: ${document.data}")
            document.getString("roomID").orEmpty()
        } else {
            Log.d("Firestore", "No such document")
            ""
        }
    } catch (exception: Exception) {
        Log.d("Firestore", "get failed with ", exception)
        ""
    }
}

// Suspend function để lấy chi tiết phòng
suspend fun getRoomDetail(tenantID: String): List<Any> {
    val rID = getRoomInfo(tenantID)
    val roomRef = db.collection("Room").document(rID)

    var rCost = 0
    var rSize = 0
    var rStatus = 0
    var rEprice = 0
    var rWprice = 0

    return try {
        val document = roomRef.get().await()
        if (document.exists()) {
            Log.d("Firestore", "Document data: ${document.data}")
            rCost = document.getLong("roomCost")?.toInt() ?: 0
            rSize = document.getLong("roomSize")?.toInt() ?: 0
            rStatus = document.getLong("roomStatus")?.toInt() ?: 0
            rEprice = document.getLong("ElecUprice")?.toInt() ?: 0
            rWprice = document.getLong("WaterUprice")?.toInt() ?: 0
        } else {
            Log.d("Firestore", "Document does not exist!")
        }
        listOf(rID, rCost, rSize, rStatus, rEprice, rWprice)
    } catch (exception: Exception) {
        Log.d("Firestore", "Error getting document: ${exception.message}")
        listOf(rID, rCost, rSize, rStatus, rEprice, rWprice)
    }
}

// Composable để hiển thị thông tin phòng

@Composable
fun RoomDetaiDisplay(tenantID: String): List<Any> {
    // Sử dụng remember để lưu trữ và cập nhật dữ liệu
    var roomDetails by remember { mutableStateOf<List<Any>>(emptyList()) }

    // Sử dụng LaunchedEffect để gọi các hàm bất đồng bộ
    LaunchedEffect(tenantID) {
        roomDetails = getRoomDetail(tenantID) // Gọi hàm lấy dữ liệu phòng
    }

    return roomDetails // Trả về roomDetails để hiển thị trong Composable
}

suspend fun countRooms(): Int {

    // Lấy reference đến collection bạn muốn đếm tài liệu
    val collectionRef = db.collection("Room")

    return try {
        // Lấy tất cả tài liệu trong collection và trả về số tài liệu
        //val querySnapshot: QuerySnapshot = collectionRef.get().await()
        collectionRef.get().await().size()
    } catch (exception: Exception) {
        // Nếu có lỗi, trả về -1
        Log.d("Firestore", "Error getting document: ${exception.message}")
        -1
    }
}

@Composable
fun countroomDisplay():Int{
    var roomDetails by remember { mutableStateOf(0) }

    // Sử dụng LaunchedEffect để gọi các hàm bất đồng bộ
    LaunchedEffect(Unit) {
        roomDetails = countRooms() // Gọi hàm lấy dữ liệu phòng
    }

    return roomDetails // Trả về roomDetails để hiển thị trong Composable
}
// ch xu li
// Hàm bất đồng bộ lấy báo cáo tài chính từ Firestore
suspend fun getFinacialReport(tenantID: String, month: Int, year: Int): List<Any> {
    // Get the room ID using the tenant ID
    val rID = getRoomInfo(tenantID) // Assuming this function exists to retrieve the room ID

    // Reference to the Bill collection for the specific year
    val db = FirebaseFirestore.getInstance()
    val billRef = db.collection("Bill").document("$year")

    // Reference to the sub-collection for the specific month and tenant's room ID
    val monthRef = billRef.collection("T$month").document(rID)

    // Variables for storing financial details
    var ElecComsumption = 0
    var waterComsumption = 0
    var InternetFee = 0
    var Service = 0

    return try {
        // Fetch the document for the specific month and tenant
        val document = monthRef.get().await()

        if (document.exists()) {
            Log.d("Firestore", "Document data: ${document.data}")
            // Extract the financial data from the document
            ElecComsumption = document.getLong("ElecComsumption")?.toInt() ?: 0
            waterComsumption = document.getLong("WaterComsumption")?.toInt() ?: 0
            InternetFee = document.getLong("Internet")?.toInt() ?: 0
            Service = document.getLong("Service")?.toInt() ?: 0
        } else {
            Log.d("Firestore", "Document does not exist!")
        }

        // Return the financial data
        listOf(rID, ElecComsumption, waterComsumption, InternetFee, Service)

    } catch (exception: Exception) {
        Log.d("Firestore", "Error getting document: ${exception.message}")
        // Return empty values in case of error
        listOf(rID, ElecComsumption, waterComsumption, InternetFee, Service)
    }
}

// Composable to display financial report asynchronously
@Composable
fun FinacialReportDisplay(tenantID: String, month: Int, year: Int): List<Any> {
    // State to store the financial data fetched from Firestore
    var roomDetails by rememberSaveable { mutableStateOf<List<Any>>(emptyList()) }

    // Use LaunchedEffect to fetch the financial report asynchronously when inputs change
    LaunchedEffect(tenantID, month, year) {
        roomDetails = getFinacialReport(tenantID, month, year)
    }

    // Return the financial data (you can render it in the UI as needed)
    return roomDetails
}

// xu li

//oooo