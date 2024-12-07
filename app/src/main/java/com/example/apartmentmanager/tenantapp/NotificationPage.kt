package com.example.apartmentmanager.tenantapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.apartmentmanager.templates.InfoPage

@Composable
fun NotificationPage(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    InfoPage(
        title = "Notifications",
        modifier = modifier,
        onBackClick = { onFunctionChange(0) },
    ) {

    }
}