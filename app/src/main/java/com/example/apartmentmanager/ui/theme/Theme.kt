package com.example.apartmentmanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Gray40,
    onPrimary = Gray130,

    secondary = Gray30,
    onSecondary = Gray140,

    background = Gray10,
    onBackground = Gray130,

    tertiary = Gray20,
    onTertiary = Gray130,

    tertiaryContainer = Gray30,
    onTertiaryContainer = Gray50,

    errorContainer = DarkRed,
    error = Gray130,

)

private val LightColorScheme = lightColorScheme(
    primary = DarkCyan,
    onPrimary = Gray140,

    secondary = Gray140,
    onSecondary = Gray10,

    background = Gray120,
    onBackground = Black,

    tertiary = Gray130,
    onTertiary = Gray20,

    tertiaryContainer = Gray130,
    onTertiaryContainer = Gray140,

    errorContainer = LightRed,
    error = Black



    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ApartmentManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}