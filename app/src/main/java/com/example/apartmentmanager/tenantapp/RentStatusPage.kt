package com.example.apartmentmanager.tenantapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apartmentmanager.templates.InfoPage
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme

//Function 3: Trang thanh toán
@Composable
fun RentStatusPage(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    InfoPage(
        title = "Rent Status",
        onBackClick = { onFunctionChange(0) },
        modifier = modifier
    ) {
        Text("Rent Status:")
        Text("Rent Start Date:")
        Text("Rent End Date:")
        Text("Rent Amount:")
        Text("Rent Status:")
        Text("Rent Note:")
        Text("Rent Last Updated:")
        Text("Deposit Status:")
        Text("Deposit Amount:")
        Text("Deposit Note:")
        Text("Deposit Last Updated:")
        Text("Other Information:")
        Text("Other Note:")
        Text("Other Last Updated:")
    }
}

@Preview(showBackground = true)
@Composable
fun RentStatusPagePreviewLightMode() {
    ApartmentManagerTheme {
        RentStatusPage(modifier = Modifier, onFunctionChange = {})
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RentStatusPagePreviewDarkMode() {
    ApartmentManagerTheme {
        RentStatusPage(modifier = Modifier, onFunctionChange = {})
    }
}