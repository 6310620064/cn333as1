package com.example.guessinggame.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green


private val DarkColorPalette = darkColors(

    background = Purple600,
    onBackground = Purple500,
    onPrimary = Purple700,
    surface = White,
    primary = Yellow,
    primaryVariant = Yellow,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    background =  White,
    onBackground = Blue900,
    onPrimary = Blue900,
    surface = Black,
    primary = Pink,
    primaryVariant = Pink,
    secondary = Pink

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun GuessingGameTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}