package com.example.apartmentmanager.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apartmentmanager.R
import com.example.apartmentmanager.util.LocaleManager

//Khuôn mẫu các thẻ chức năng
@Composable
fun InfoCard(
    icon: ImageVector?,
    painter: Painter?,
    tint: Color,
    scale: Float,
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
            .padding(cardPadding)
            .width(screenWidth * 0.4f)
            .height(screenWidth * 0.4f),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .padding(cardPadding)
                .width(screenWidth * 0.3f)
                .height(screenWidth * 0.3f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .scale(1.5f).height(screenWidth * 0.1f)
                        //.padding(bottom = cardPadding, top = cardPadding * 0.8f),
                            ,
                    tint = tint
                )
            }
            if (painter != null) {
                Icon(
                    painter = painter,
                    contentDescription = title,
                    modifier = Modifier
                        .scale(scale).height(screenWidth * 0.12f)
                        //.padding(bottom = cardPadding, top = cardPadding * 0.8f),
                            ,
                    tint = tint
                )
            }
            Spacer(modifier = Modifier.height(screenWidth * 0.05f))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
    InfoCard(
        //icon = Icons.Default.Info,
        icon = null,
        painter = painterResource(R.drawable.coin),
        //painter = null,
        tint = Color.Unspecified,
        scale = 0.5f,
        title = "Rent Status",
        onClick = {}

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCard3() {
    InfoCard(
        //icon = Icons.Default.Info,
        icon = null,
        painter = painterResource(R.drawable.coin),
        //painter = null,
        tint = Color.Unspecified,
        scale = 0.5f,
        title = "Rent Status Information",
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCard2() {
    InfoCard(
        icon = Icons.Default.Info,
        //icon = null,
        //painter = painterResource(R.drawable.coin),
        painter = null,
        tint = Color.Unspecified,
        scale = 0.5f,
        title = "Modify Room Information",
        onClick = {}
    )
}