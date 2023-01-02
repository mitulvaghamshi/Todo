package me.mitul.todo.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val BrightOrange = Color(0xFFFF8A65)
val MediumOrange = Color(0xFFFFA000)
val DarkOrange = Color(0xFFF57C00)

private val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)

private val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
)

private val DarkColorPalette = darkColors(
    primary = BrightOrange,
    primaryVariant = MediumOrange,
    secondary = DarkOrange,
)

private val LightColorPalette = lightColors(
    primary = BrightOrange,
    primaryVariant = MediumOrange,
    secondary = DarkOrange,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) = MaterialTheme(
    colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    },
    typography = Typography,
    shapes = Shapes,
    content = content,
)

@Preview
@Composable
private fun Light_AppTheme_Preview() = AppTheme(darkTheme = false) {
    Surface(
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        Text(text = "Light Theme")
    }
}

@Preview
@Composable
private fun Dark_AppTheme_Preview() = AppTheme(darkTheme = true) {
    Surface(
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        Text(text = "Dark Theme")
    }
}
