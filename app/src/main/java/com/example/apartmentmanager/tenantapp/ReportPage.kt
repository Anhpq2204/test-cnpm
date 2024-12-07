package com.example.apartmentmanager.tenantapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apartmentmanager.templates.InfoPage
import com.example.apartmentmanager.ui.theme.ApartmentManagerTheme

//Function 6: Gửi báo cáo
@Composable
fun ReportPage(
    modifier: Modifier,
    onFunctionChange: (Int) -> Unit
) {
    InfoPage(
        modifier = modifier,
        onBackClick = { onFunctionChange(0) },
        title = "Send Report"
    ) {
        var title by rememberSaveable { mutableStateOf("") }
        var content by rememberSaveable { mutableStateOf("") }
        Text("Report Title:")
        TextField(
            value = title,
            onValueChange = {title = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            singleLine = true
        )
        Text("Description:")
        TextField(
            value = content,
            onValueChange = {content = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text("Attachment:")
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = { /* Handle send report button click */ },
            modifier = Modifier.align(Alignment.End).padding(top = 20.dp)
        ) {
            Text("Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportPagePreviewLightMode() {
    ApartmentManagerTheme {
        ReportPage(modifier = Modifier, onFunctionChange = {})
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ReportPagePreviewDarkMode() {
    ApartmentManagerTheme {
        ReportPage(modifier = Modifier, onFunctionChange = {})
    }
}