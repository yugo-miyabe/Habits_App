package jp.yuyuyu.habits

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.yuyuyu.habits.navigation.HabitsNavHost
import jp.yuyuyu.habits.theme.HabitsTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    HabitsTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            HabitsNavHost()
        }
    }
}
