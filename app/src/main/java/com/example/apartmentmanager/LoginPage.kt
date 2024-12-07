package com.example.apartmentmanager

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apartmentmanager.ui.theme.*
import com.google.firebase.firestore.FirebaseFirestore

//Giao diện trang đăng nhập
@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    onLoginClick: (Int, String) -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var loading by rememberSaveable { mutableStateOf(false) }
    val filled = username.isNotEmpty() && password.isNotEmpty()
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Ảnh nền phía trên, chiếm 25% chiều cao
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
        )

        // Giao diện đăng nhập chiếm 75% chiều cao, bắt đầu từ 25% chiều cao
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to Apartment!",
                modifier = Modifier.padding(top = 80.dp, bottom = 40.dp),
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Trường nhập tên đăng nhập và mật khẩu
            UsernameBar(username = username, onUsernameChange = { username = it })

            PasswordBar(password = password, onPasswordChange = { password = it })

            // Các nút đăng nhập và đăng ký
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Nút đăng ký
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.width(screenWidth * 0.25f)
                ) {
                    Text("Sign up")
                }

                // Nút đăng nhập
                Button(
                    onClick = {
                        loading = true
                        isValid(username, password) { role, userID ->
                            loading = false
                            when (role) {
                                0 -> {
                                    showErrorDialog = true
                                }
                                else -> {
                                    onLoginClick(role, userID)
                                }
                            }
                        }
                    },
                    modifier = Modifier.width(screenWidth * 0.25f),
                    // Chỉ nhấn được khi đã nhập cả username và password
                    enabled = filled
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(
                            text = "Sign In",
                        )
                    }
                }
            }

            //Quên mật khẩu
            Text(
                text = "Forgot password?",
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodySmall
            )

            //Thông báo nhập sai tên đăng nhập hoặc mật khẩu
            // Phần bên trong LaunchedEffect sẽ chạy dưới nền, bắt đầu từ khi có thông báo lỗi
            LaunchedEffect(showErrorDialog) {
                if (showErrorDialog) {
                    //Đợi 2 giây trước khi ẩn thông báo lỗi
                    kotlinx.coroutines.delay(2000)
                    showErrorDialog = false
                }
            }
            AnimatedVisibility(
                visible = showErrorDialog,
                //Tạo hiệu ứng xuất hiện và biến mất
                exit = fadeOut(tween(1000))
            ) {
                ElevatedCard(
                    modifier = modifier
                        .height(150.dp)
                        .width(300.dp)
                        .padding(top = 30.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.errorContainer)
                ) {
                    IconButton(
                        onClick = { showErrorDialog = false },
                        modifier = modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close dialog",
                        )
                    }
                    Text(
                        text = "Username or password invalid!",
                        color = MaterialTheme.colorScheme.error,
                        modifier = modifier.padding(horizontal = 20.dp)
                    )
                }
            }

        }
    }
}


//Trường nhập tên đăng nhập
@Composable
private fun UsernameBar(
    username: String,
    modifier: Modifier = Modifier,
    onUsernameChange: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    TextField(
        shape = ShapeDefaults.ExtraLarge,
        value = username,
        onValueChange = onUsernameChange,
        placeholder = { Text("Your username...") },
        label = { Text("Username") },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier.padding(vertical = 10.dp).width(screenWidth * 0.8f)
    )
}


//Trường nhập mật khẩu
@Composable
private fun PasswordBar(
    password: String,
    modifier: Modifier = Modifier,
    onPasswordChange: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var showPassword by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier.width(screenWidth * 0.8f),
    ) {
        TextField(
            shape = ShapeDefaults.ExtraLarge,
            value = password,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = onPasswordChange,
            placeholder = { Text("Your password...") },
            label = { Text("Password") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = modifier.padding(vertical = 10.dp).width(screenWidth * 0.6f)
        )

        Spacer(modifier = Modifier.width(screenWidth * 0.04f))
        // Nút hiển thị mật khẩu
        IconButton(
            onClick = { showPassword = !showPassword },
            modifier = modifier.align(Alignment.CenterVertically).scale(1.2f)
        ) {
            Icon(
                painter = painterResource(
                    id = if (showPassword) R.drawable.show_password else R.drawable.hide_password),
                contentDescription = "Show password",
                tint = MaterialTheme.colorScheme.onTertiary,
            )
        }
    }

}

// Kiểm tra tên đăng nhập và mật khẩu có hợp lệ hay không
fun isValid(username: String, password: String, onResult: (Int, String) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val userRef = db.collection("authentication")
    var userID = ""

    userRef.get()
        .addOnSuccessListener { result ->
            var isValid = false
            for (document in result) {
                if (document.getString("username") == username && document.getString("password") == password) {
                    isValid = true
                    userID = document.id
                    break
                }
            }
            if (userID.isNotEmpty()) {
                when (userID[0]) {
                    'T' -> onResult(1, userID)
                    'M' -> onResult(2, userID)
                    'O' -> onResult(3, userID)
                    else -> onResult(0, "") // Trả về 0 nếu không hợp lệ
                }
            } else {
                onResult(0, "") // Không tìm thấy tài khoản phù hợp
            }
        }
        .addOnFailureListener { exception ->
            // Thông báo lỗi ra logcat nếu có
            Log.d("Firestore", "Error getting documents: ", exception)
            onResult(0, "") // Trả về false nếu có lỗi xảy ra
        }
}


@Preview(showBackground = true)
@Composable
fun LoginPreviewLightMode() {
    ApartmentManagerTheme {
        LoginPage(onLoginClick = { _, _ -> })
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginPreviewDarkMode() {
    ApartmentManagerTheme {
        LoginPage(onLoginClick = { _, _ -> })
    }
}