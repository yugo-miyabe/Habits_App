package jp.yuyuyu.habits.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object AppTheme {
    val colors: HabitsColors
        @Composable
        @ReadOnlyComposable
        get() = LocaleColor.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocaleTypography.current
}


@Composable
internal fun HabitsTheme(
    color: HabitsColors = AppTheme.colors,
    typography: Typography = AppTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberColor = remember {
        color.copy()
    }

    val rememberTypography = remember {
        typography.copy()
    }

    CompositionLocalProvider(
        LocaleColor provides rememberColor,
        LocaleTypography provides rememberTypography
    ) {
        content()
    }
}