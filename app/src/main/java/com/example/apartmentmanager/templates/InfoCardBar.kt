package com.example.apartmentmanager.templates

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//Khuôn mẫu các thẻ chức năng
@Composable
fun InfoCardBar(
    icon: ImageVector? = null,
    painter: Painter? = null,
    tint: Color,
    size: Float = 0.2f,
    textColor: Color = MaterialTheme.colorScheme.onSecondary,
    title: String,
    onClick: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardPadding = screenWidth * 0.05f
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .padding(top = cardPadding, start = cardPadding, end = cardPadding)
            .width(screenWidth * 0.9f),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(cardPadding).align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically // Căn giữa các phần tử theo chiều dọc
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier.height(screenWidth * size).width(screenWidth * size),
                    tint = tint
                )
            }
            if (painter != null) {
                Icon(
                    painter = painter,
                    contentDescription = title,
                    modifier = Modifier.height(screenWidth * size).width(screenWidth * size),
                    tint = tint
                )
            }

            Spacer(modifier = Modifier.width(screenWidth * 0.05f))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }
}