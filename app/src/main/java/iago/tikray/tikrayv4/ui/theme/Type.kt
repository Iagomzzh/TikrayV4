package iago.tikray.tikrayv4.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import iago.tikray.tikrayv4.R


val tituloTikray = FontFamily(
    Font(R.font.kodemono_semibold, FontWeight.Bold),
    Font(R.font.kodemonor_egular, FontWeight.Normal),
    Font(R.font.kodemono_bold, FontWeight.ExtraBold),
)


// Set of Material typography styles to start with


val arvo = FontFamily(
    Font(R.font.arvo_bold, FontWeight.Bold),
    Font(R.font.arvo_regular, FontWeight.Normal),
    Font(R.font.kodemonor_egular, FontWeight.ExtraBold),
)
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = tituloTikray,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)


   /* val TitleTypographic = Typography (

        titleLarge = TextStyle(
            fontFamily = tituloTikray,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        )
        )*/
/*
labelSmall = TextStyle(
fontFamily = FontFamily.Default,
fontWeight = FontWeight.Medium,
fontSize = 11.sp,
lineHeight = 16.sp,
letterSpacing = 0.5.sp
)
*/
