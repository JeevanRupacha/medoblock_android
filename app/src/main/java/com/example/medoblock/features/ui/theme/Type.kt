package com.example.medoblock.features.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.medoblock.R
import com.example.medoblock.features.shared.utils.Constants.GOOGLE_FONT_PROVIDER_AUTHORITY
import com.example.medoblock.features.shared.utils.Constants.GOOGLE_FONT_PROVIDER_PACKAGE

val provider = GoogleFont.Provider(
    providerAuthority = GOOGLE_FONT_PROVIDER_AUTHORITY,
    providerPackage = GOOGLE_FONT_PROVIDER_PACKAGE,
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Roboto")
val googleRoboto = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

val Poppins = FontFamily(
    androidx.compose.ui.text.font.Font(R.font.poppins_light, weight = FontWeight.Light),
    androidx.compose.ui.text.font.Font(R.font.poppins_regular, weight = FontWeight.Normal),
    androidx.compose.ui.text.font.Font(R.font.poppins_medium, weight = FontWeight.Medium),
    androidx.compose.ui.text.font.Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
    androidx.compose.ui.text.font.Font(R.font.poppins_bold, weight = FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography2 = Typography(
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.6.sp,
    ),
    displayMedium =  TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.6.sp,
    ),
    displaySmall =  TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.6.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.6.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.6.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.6.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.6.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 16.sp
    ),

    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.6.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.6.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.6.sp,
    )
)

val Typography.bodyOLarge: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.W400,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.6.sp,
        )
    }

val Typography.bodyOMedium: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.W400,
            fontSize = 15.sp,
            letterSpacing = 0.6.sp,
            lineHeight = 20.sp
        )
    }

val Typography.bodyOSmall: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = Poppins,
            fontWeight = FontWeight.W400,
            fontSize = 13.sp,
            letterSpacing = 0.6.sp,
            lineHeight = 16.sp
        )
    }