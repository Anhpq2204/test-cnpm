package com.example.apartmentmanager.tenantapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apartmentmanager.R
import com.example.apartmentmanager.templates.InfoCard
import com.example.apartmentmanager.templates.InfoCardBar
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme
import com.example.apartmentmanager.ui.theme.Red


//Function 0: Trang chủ
@Composable
@OptIn(ExperimentalLayoutApi::class)
fun HomePage(
    modifier: Modifier,
    onLogOut: () -> Unit,
    onFunctionChange: (Int) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        item {
            HeaderPane(modifier, onFunctionChange)
        }

        item {
            FlowRow(
                modifier = Modifier.padding(top = screenWidth * 0.05f)
            ) {
                //Các thẻ chức năng xuất hiện trong menu
                ApartmentInfoCard(onFunctionChange)
                RoomInfoCard(onFunctionChange)
                RentStatusCard(onFunctionChange)
                FinancialReportCard(onFunctionChange)
                ModifyInfoCard(onFunctionChange)
                SendReportCard(onFunctionChange)
            }

        }

        item {
            SettingBar(onFunctionChange)
            LogOutBar(onLogOut)
        }
        item {
            Spacer(modifier = modifier.height(20.dp))
        }
    }
}

//25% màn hình bên trên của menu để hiện các thông tin chính
@Composable
fun HeaderPane(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(screenHeight * 0.25f)
            .background(color = MaterialTheme.colorScheme.primary),
    ) {
        IconButton(
            onClick = { onFunctionChange(8) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .scale(1f),
        ) {
            Icon(
                painter = painterResource(R.drawable.notifications),
                contentDescription = "Notifications",
                tint = Color.Unspecified,
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 30.dp, top = 30.dp)
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = "My Apartment",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "lorem ipsum.",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Icon(
            painter = painterResource(R.drawable.apartment2),
            contentDescription = "Home",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(screenWidth * 0.35f)
                .scale(1f)
                .padding(end = 20.dp, top = 30.dp),
            tint = Color.Unspecified
        )
    }
}



//Các thẻ chức năng
@Composable
fun ApartmentInfoCard(onFunctionChange: (Int) -> Unit) {
    InfoCard(
        icon = null,
        painter = painterResource(R.drawable.apartment),
        tint = Color.Unspecified,
        scale = 1f,
        title = "Apartment Information",
        onClick = {onFunctionChange(1)}
    )
}

@Composable
fun RoomInfoCard(onFunctionChange: (Int) -> Unit) {
    InfoCard(
        icon = null,
        painter = painterResource(R.drawable.room),
        tint = Color.Unspecified,
        scale = 1f,
        title = "Room Information",
        onClick = {onFunctionChange(2)}
    )
}

@Composable
fun RentStatusCard(onFunctionChange: (Int) -> Unit) {
    InfoCard(
        //icon = Icons.Default.Info,
        icon = null,
        painter = painterResource(R.drawable.coin),
        //painter = null,
        tint = Color.Unspecified,
        scale = 1f,
        title = "Rent Status",
        onClick = {onFunctionChange(3)}
    )
}

@Composable
fun FinancialReportCard(onFunctionChange: (Int) -> Unit) {
    InfoCard(
        icon = null,
        painter = painterResource(R.drawable.financial_report),
        tint = Color.Unspecified,
        scale = 1f,
        title = "Financial Report",
        onClick = {onFunctionChange(4)}
    )
}

@Composable
fun ModifyInfoCard(onFunctionChange: (Int) -> Unit) {
    InfoCard(
        icon = null,
        painter = painterResource(R.drawable.modify),
        tint = Color.Unspecified,
        scale = 1f,
        title = "Modify Room Information",
        onClick = {onFunctionChange(5)}
    )
}

@Composable
fun SendReportCard(onFunctionChange: (Int) -> Unit) {
    InfoCard(
        icon = null,
        painter = painterResource(R.drawable.warning),
        tint = Color.Unspecified,
        scale = 1f,
        title = "Send Report",
        onClick = {onFunctionChange(6)}
    )
}

@Composable
fun SettingBar(
    onFunctionChange: (Int) -> Unit
) {
    InfoCardBar(
        icon = Icons.TwoTone.Settings,
        tint = MaterialTheme.colorScheme.onSecondary,
        title = "Settings",
        size = 0.07f,
        onClick = {onFunctionChange(7)}
    )
}

@Composable
fun LogOutBar(
    onLogOut: () -> Unit
) {
    InfoCardBar(
        icon = Icons.TwoTone.ExitToApp,
        tint = Red,
        size = 0.07f,
        textColor = Color.Red,
        title = "Log out",
        onClick = onLogOut
    )
}


@Preview(showBackground = true)
@Composable
fun HomePagePreviewLightMode() {
    ApartmentManagerTheme {
        HomePage(modifier = Modifier, onLogOut = {}, onFunctionChange = {})

    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePagePreviewDarkMode() {
    ApartmentManagerTheme {
        HomePage(modifier = Modifier, onLogOut = {}, onFunctionChange = {})
    }
}