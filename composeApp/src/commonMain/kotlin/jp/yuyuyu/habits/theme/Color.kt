package jp.yuyuyu.habits.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class HabitsColors(
    val white: Color = Color(0xFFFFFFFF),
    val black: Color = Color(0xFF000000),
    val textBaseFont: Color = Color(0xFFD3D3D3),
    val textBaseBackground: Color = Color(0xFFFFFFFF),
    val textPinkFont: Color = Color(0xFFDA85AF),
    val textPinkBackground: Color = Color(0xFFEAB8E3),
    val textBlueFont: Color = Color(0xFFA6B8E1),
    val textBlueBackground: Color = Color(0xFFA6B8E1),
    val textPurpleFont: Color = Color(0xFFAE8FB1),
    val textPurpleBackground: Color = Color(0xFFBEDFFF),
    val sunday: Color = Color(0xFFDC143C),
    val saturday: Color = Color(0xFF4169E1),
)

internal val LocaleColor = staticCompositionLocalOf { HabitsColors() }
