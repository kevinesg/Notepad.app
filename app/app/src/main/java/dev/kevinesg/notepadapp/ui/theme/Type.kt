package dev.kevinesg.notepadapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.kevinesg.notepadapp.R

// Set of Material typography styles to start with

val fonts = FontFamily(
    Font(R.font.dejavu_sans_bold, weight = FontWeight.Bold),
    Font(R.font.dejavu_sans_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.dejavu_sans_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.dejavu_sans_regular, weight = FontWeight.Normal)
)

val Typography = Typography(
    defaultFontFamily = fonts,
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    // Other default text styles to override
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)

